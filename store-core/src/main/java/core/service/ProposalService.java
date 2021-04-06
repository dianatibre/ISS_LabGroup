package core.service;

import core.domain.Bidding;
import core.domain.Conference;
import core.domain.Proposal;
import core.repository.BiddingRepoI;
import core.repository.ConferenceRepoI;
import core.repository.ProposalRepoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    ConferenceRepoI conferenceRepoI;


    // Add a new proposal
    // return false if proposal already exists
    // return true is proposal is saved successfully
    public boolean addProposal(Proposal p) throws Exception {
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
    public boolean updateProposal(Proposal p) throws Exception {
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

    public boolean addBidding(Bidding bidding) throws Exception {
        try {
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

        } catch (Exception e) {
            throw new Exception("Bidding adding failed!");
        }
        return true;
    }


}
