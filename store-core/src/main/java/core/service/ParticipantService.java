package core.service;

import core.domain.*;
import core.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepoI repo;

    @Autowired
    private FeeRepoI feeRepo;

    @Autowired
    private AuthorRepoI authorRepo;

    @Autowired
    private SpeakerRepoI speakerRepo;

    @Autowired
    private ListenerRepoI listenerRepo;

    @Autowired
    private LoginRepoI loginRepo;

    @Autowired
    private ConferenceRepoI conferenceRepo;


    public Optional<Participant> findOneParticipant(Integer id) {
        return repo.findById(id);
    }

    @Transactional
    public boolean addConferenceFee(Fee f) {
        try {
            Conference c = conferenceRepo.findById(f.getConference().getId()).get();
            List<Fee> fees = c.getFees();
            Fee nf = Fee.builder()
                    .conference(c)
                    .date(f.getDate())
                    .participant(f.getParticipant())
                    .build();
            fees.add(nf);
            c.setFees(fees);
            conferenceRepo.save(c);
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public boolean addAuthor(Author author) {
        try {
            System.out.println("aici" + author.toString());
            loginRepo.save(author.getLogin());
            authorRepo.save(author);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean addProposalToAuthor(Author author, Proposal proposal) {
        author.setProposal(proposal);
        return true;
    }

    public boolean addPaperToProposal(Proposal proposal, Paper paper) {
        proposal.setPaper(paper);
        return true;
    }

    public Optional<Author> getAuthor(int participantId) {
        Optional<Author> p = authorRepo.findById(participantId);
        if (p.isPresent())
            return authorRepo.findById(participantId);
        else
            return Optional.empty();
    }

    public boolean addListener(Listener listener) {
//        Optional<Listener> p = listenerRepo.findById(listener.getId());
//        if (p.isPresent())
//            return false;
        try {
            loginRepo.save(listener.getLogin());
            listenerRepo.save(listener);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Transactional
    public Optional<Listener> getListener(int participantId) {
        Optional<Listener> p = listenerRepo.findById(participantId);
        if (p.isPresent())
            return listenerRepo.findById(participantId);
        else
            return Optional.empty();
    }

    @Transactional
    public Optional<Participant> getParticipantByUsername(String username) {
        Optional<Login> login = loginRepo.findAll().stream().filter(l -> l.getUsername().equals(username)).findAny();
        Optional<Author> author = authorRepo.findAll().stream().filter(p -> p.getLogin().equals(login.get())).findAny();
        if(author.isPresent()) {
            Participant p = (Participant) author.get();
            return Optional.of(p);
        }
        else {
            Optional<Listener> listener = listenerRepo.findAll().stream().filter(p -> p.getLogin().equals(login.get())).findAny();
            System.out.println(listenerRepo.findAll());
            if(listener.isPresent()) {
                Participant p = (Participant) listener.get();
                return Optional.of(p);
            }
        }
        return null;
    }
}

