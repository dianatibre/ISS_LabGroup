package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.domain.Bidding;
import ro.ubb.catalog.core.domain.Proposal;
import ro.ubb.catalog.core.domain.Reviewer;
import ro.ubb.catalog.core.repository.BiddingRepoI;
import ro.ubb.catalog.core.repository.ConferenceRepoI;
import ro.ubb.catalog.core.repository.ProposalRepoI;
import ro.ubb.catalog.core.repository.ReviewerRepoI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProposalService {

    @Autowired
    private ProposalRepoI proposalRepo;

    @Autowired
    private BiddingRepoI biddingRepo;

    @Autowired
    private ConferenceRepoI conferenceRepoI;

    @Autowired
    private ReviewerRepoI reviewerRepoI;

    // Add a new proposal
    // return false if proposal already exists
    // return true is proposal is saved successfully
    public boolean addProposal(Proposal p) {
        if (p.getTitle().equals("") || p.getKeywords().equals("") || p.getTopics().equals("") || p.getAbstractProposal().equals("")
                || !conferenceRepoI.findById(p.getConference().getId()).isPresent())
            return false;
        Optional<Proposal> proposal = proposalRepo.findById(p.getId());
        if (proposal.isPresent())
            return false;
        proposalRepo.save(p);
        return true;
    }


    // Updates a proposal
    // return false is that proposal doesn't exist
    // return true if proposal is updated successfully

    @Transactional
    public boolean updateProposal(Proposal p) {
        if (p.getTitle().equals("") || p.getKeywords().equals("") || p.getTopics().equals("") || p.getAbstractProposal().equals("") || !conferenceRepoI.findById(p.getConference().getId()).isPresent())
            return false;
        Optional<Proposal> proposal = proposalRepo.findById(p.getId());
        if (proposal.isPresent()) {
            this.proposalRepo.findById(p.getId()).ifPresent(p1 -> {
                p1.setTitle(p.getTitle());
                p1.setKeywords(p.getKeywords());
                p1.setTopics(p.getTopics());
                p1.setAbstractProposal(p.getAbstractProposal());
            });
            return true;
        }
        return false;

    }


    /// Deletes a proposal
    // return false if that proposal doesn't exist
    // return true if that proposal is deleted successfully
    public boolean deleteProposal(Integer id) {
        Optional<Proposal> proposal = proposalRepo.findById(id);
        if (proposal.isPresent()) {
            proposalRepo.deleteById(id);
            return true;
        }
        return false;

    }


    // Returns an optional whether that proposal exists or not based on the given id
    public Optional<Proposal> findOneProposal(Integer id) {
        return proposalRepo.findById(id);
    }


    // Returns a list of all proposals
    public List<Proposal> getProposals() {
        return new ArrayList<>(this.proposalRepo.findAll());
    }

    public boolean addBidding(Bidding bidding) {
        if (bidding.getResult().equals("") ||
                !proposalRepo.findById(bidding.getProposal().getId()).isPresent() ||
                !reviewerRepoI.findById(bidding.getReviewer().getId()).isPresent())
            return false;
        Proposal proposal = proposalRepo.findById(bidding.getProposal().getId()).get();
        List<Bidding> biddings = proposal.getBiddings();
        Bidding nb = Bidding.builder()
                .reviewer(bidding.getReviewer())
                .proposal(proposal)
                .result(bidding.getResult())
                .build();
        biddings.add(nb);
        biddingRepo.save(bidding);
        proposal.setBiddings(biddings);
        proposalRepo.save(proposal);
        return true;
    }

    @Transactional
    public boolean updateBidding(Bidding bidding) {
        if (bidding.getResult().equals("") ||
                !proposalRepo.findById(bidding.getProposal().getId()).isPresent() ||
                !reviewerRepoI.findById(bidding.getReviewer().getId()).isPresent())
            return false;
        Optional<Bidding> bid = biddingRepo.findById(bidding.getId());
        if (bid.isPresent()) {
            this.biddingRepo.findById(bidding.getId()).ifPresent(b -> {
                b.setProposal(bidding.getProposal());
                b.setResult(bidding.getResult());
                b.setReviewer(bidding.getReviewer());
            });
            return true;
        }
        return false;

    }

    /// Deletes a bidding
    // return false if that bidding doesn't exist
    // return true if that bidding is deleted successfully

    public boolean deleteBidding(Integer id) {
        Optional<Bidding> bidding = biddingRepo.findById(id);
        if (bidding.isPresent()) {
            biddingRepo.deleteById(id);
            return true;
        }
        return false;

    }

    public List<Bidding> getBiddings() {
        return new ArrayList<>(this.biddingRepo.findAll());
    }

    public boolean addReviewerForProposal(Reviewer reviewer, Proposal proposal) {
        Optional<Reviewer> reviewer1 = reviewerRepoI.findById(reviewer.getId());
        Optional<Proposal> proposal1 = proposalRepo.findById(proposal.getId());
        if (proposal1.isPresent() && reviewer1.isPresent()) {
            Bidding bidding = Bidding.builder()
                    .reviewer(reviewer)
                    .proposal(proposal)
                    .result("")
                    .build();
            biddingRepo.save(bidding);
            return true;
        }
        return false;
    }

}
