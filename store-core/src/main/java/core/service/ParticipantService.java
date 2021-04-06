package core.service;

import core.domain.*;
import core.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepoI participantRepo;

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

    //  ------------ Participant -----------------
    public Optional<Participant> findOneParticipant(Integer id) {
        return participantRepo.findById(id);
    }

    public List<Participant> getParticipants() { return new ArrayList<>(this.participantRepo.findAll()); }

    public boolean addParticipant(Participant newParticipant)
    {
        if(newParticipant.getFees().size() == 0 || newParticipant.getFirstName().equals("") || newParticipant.getLastName().equals("") || newParticipant.getAge() < 0 || newParticipant.getAffiliation().equals(""))
        {
            return false;
        }
        Optional<Participant> auxParticipant = this.participantRepo.findById(newParticipant.getId());
        if (auxParticipant.isPresent())
        {
            return false;
        }
        this.participantRepo.save(newParticipant);
        return true;
    }

    public boolean deleteParticipant(Integer id)
    {
        Optional<Participant> auxParticipant = this.participantRepo.findById(id);
        if (auxParticipant.isPresent())
        {
            this.participantRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateParticipant(Participant newParticipant)
    {
        if(newParticipant.getFees().size() == 0 || newParticipant.getFirstName().equals("") || newParticipant.getLastName().equals("") || newParticipant.getAge() < 16 || newParticipant.getAffiliation().equals(""))
        {
            return false;
        }

        this.participantRepo.findById(newParticipant.getId()).ifPresent(p->{
            p.setFees(newParticipant.getFees());
            p.setFirstName(newParticipant.getFirstName());
            p.setLastName(newParticipant.getLastName());
            p.setAge(newParticipant.getAge());
            p.setAffiliation(newParticipant.getAffiliation());
            p.setLogin(newParticipant.getLogin());
        });
        return true;
    }

    //  ------------ Fee -----------------
    public Optional<Fee> findOneFee(Integer id) { return feeRepo.findById(id); }

    public List<Fee> getFees() { return new ArrayList<>(this.feeRepo.findAll()); }

    public boolean addConferenceFee(Fee newFee)
    {
        Optional<Fee> auxFee = this.feeRepo.findById(newFee.getId());
        if (auxFee.isPresent())
        {
            return false;
        }
        this.feeRepo.save(newFee);
        return true;
    }

    public boolean deleteConferenceFee(Integer id)
    {
        Optional<Fee> auxFee = this.feeRepo.findById(id);
        if (auxFee.isPresent())
        {
            this.feeRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateConferenceFee(Fee newFee)
    {
        this.feeRepo.findById(newFee.getId()).ifPresent(f->{
            f.setConference(newFee.getConference());
            f.setParticipant(newFee.getParticipant());
            f.setDate(newFee.getDate());
        });
        return true;
    }

    //  ------------ Author -----------------
    public Optional<Author> findOneAuthor(Integer id) { return authorRepo.findById(id); }

    public List<Author> getAuthors() { return authorRepo.findAll(); }

    public boolean addAuthor(Author newAuthor) {
        if (newAuthor.getFirstName().equals("") || newAuthor.getLastName().equals("") || newAuthor.getAge() < 16 || newAuthor.getAffiliation().equals(""))
        {
            return false;
        }
        Optional<Author> auxAuthor = this.authorRepo.findById(newAuthor.getId());
        if (auxAuthor.isPresent())
        {
            return false;
        }
        this.authorRepo.save(newAuthor);
        return true;
    }

    public boolean deleteAuthor(Integer id)
    {
        Optional<Author> auxAuthor = this.authorRepo.findById(id);
        if (auxAuthor.isPresent())
        {
            this.authorRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateAuthor(Author newAuthor)
    {
        if (newAuthor.getFirstName().equals("") || newAuthor.getLastName().equals("") || newAuthor.getAge() < 16 || newAuthor.getAffiliation().equals(""))
        {
            return false;
        }

        this.authorRepo.findById(newAuthor.getId()).ifPresent(a->{
            a.setFees(newAuthor.getFees());
            a.setFirstName(newAuthor.getFirstName());
            a.setLastName(newAuthor.getLastName());
            a.setAge(newAuthor.getAge());
            a.setAffiliation(newAuthor.getAffiliation());
            a.setLogin(newAuthor.getLogin());
            a.setProposal(newAuthor.getProposal());
        });
        return true;
    }

    //  ------------ Speaker -----------------
    public Optional<Speaker> findOneSpeakerByID(Integer id) { return this.speakerRepo.findById(id); }

    public List<Speaker> getSpeakers() { return new ArrayList<>(this.speakerRepo.findAll()); }

    public boolean addSpeaker(Speaker newSpeaker)
    {
        if (newSpeaker.getFirstName().equals("") || newSpeaker.getLastName().equals("") || newSpeaker.getAge() < 16 || newSpeaker.getAffiliation().equals("") || newSpeaker.getPresentation().equals(""))
        {
            return false;
        }
        Optional<Speaker> auxSpeaker = this.speakerRepo.findById(newSpeaker.getId());
        if (auxSpeaker.isPresent())
        {
            return false;
        }
        this.speakerRepo.save(newSpeaker);
        return true;
    }

    public boolean deleteSpeaker(Integer id)
    {
        Optional<Speaker> auxSpeaker = this.speakerRepo.findById(id);
        if (auxSpeaker.isPresent())
        {
            this.speakerRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateSpeaker(Speaker newSpeaker)
    {
        if (newSpeaker.getFirstName().equals("") || newSpeaker.getLastName().equals("") || newSpeaker.getAge() < 16 || newSpeaker.getAffiliation().equals("") || newSpeaker.getPresentation().equals(""))
        {
            return false;
        }
        this.speakerRepo.findById(newSpeaker.getId()).ifPresent(s->{
            s.setFees(newSpeaker.getFees());
            s.setFirstName(newSpeaker.getFirstName());
            s.setLastName(newSpeaker.getLastName());
            s.setAge(newSpeaker.getAge());
            s.setAffiliation(newSpeaker.getAffiliation());
            s.setLogin(newSpeaker.getLogin());
            s.setProposal(newSpeaker.getProposal());
            s.setPresentation(newSpeaker.getPresentation());
            s.setSection(newSpeaker.getSection());
            s.setPaper(newSpeaker.getPaper());
        });
        return true;
    }
    //  ------------ Listener -----------------
    public Optional<Listener> findOneListener(Integer id) { return this.listenerRepo.findById(id); }

    public List<Listener> getListeners() { return new ArrayList<>(this.listenerRepo.findAll()); }

    public boolean addListener(Listener newListener)
    {
        if (newListener.getFirstName().equals("") || newListener.getLastName().equals("") || newListener.getAge() < 16 || newListener.getAffiliation().equals(""))
        {
            return false;
        }
        Optional<Listener> auxListener = this.listenerRepo.findById(newListener.getId());
        if(auxListener.isPresent())
        {
            return false;
        }
        this.listenerRepo.save(newListener);
        return true;
    }

    public boolean deleteListener(Integer id)
    {
        Optional<Listener> auxListener = this.listenerRepo.findById(id);
        if(auxListener.isPresent())
        {
            this.listenerRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateListener(Listener newListener)
    {
        if (newListener.getFirstName().equals("") || newListener.getLastName().equals("") || newListener.getAge() < 16 || newListener.getAffiliation().equals(""))
        {
            return false;
        }
        this.listenerRepo.findById(newListener.getId()).ifPresent(l->{
            l.setFees(newListener.getFees());
            l.setFirstName(newListener.getFirstName());
            l.setLastName(newListener.getLastName());
            l.setAge(newListener.getAge());
            l.setAffiliation(newListener.getAffiliation());
            l.setLogin(newListener.getLogin());
            l.setSectionId(newListener.getSectionId());
        });
        return true;
    }

    //  ------------ Login -----------------

    public Optional<Login> findOneLogin(Integer id) { return this.loginRepo.findById(id); }

    public List<Login> getLogins() { return new ArrayList<>(this.loginRepo.findAll()); }

    public boolean addLogin(Login newLogin)
    {
        if(newLogin.getUsername().equals("") || newLogin.getPassword().equals(""))
        {
            return false;
        }
        List<Login> logins = this.loginRepo.findAll();
        if(logins.contains(newLogin))
        {
            return false;
        }
        this.loginRepo.save(newLogin);
        return true;
    }

    public boolean deleteLogin(String username)
    {
        List<Login> logins = this.loginRepo.findAll();
        for(Login login : logins)
        {
            if(login.getUsername().equals(username))
            {
                this.loginRepo.delete(login);
                return true;
            }
        }
        return false;
    }

    public boolean updateLogin(Login newLogin)
    {
        List<Login> logins = this.loginRepo.findAll();
        for(Login login : logins)
        {
            if(login.getUsername().equals(newLogin.getUsername()))
            {
                login.setParticipant(newLogin.getParticipant());
                login.setPassword(newLogin.getPassword());
                login.setPcMember(newLogin.getPcMember());
                return true;
            }
        }
        return false;
    }

    public boolean addProposalToAuthor(Author author, Proposal proposal) {
        author.setProposal(proposal);
        return true;
    }

    public boolean addPaperToProposal(Proposal proposal, Paper paper) {
        proposal.setPaper(paper);
        return true;
    }

}

