package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.domain.*;
import ro.ubb.catalog.core.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class PCMemberService {

    @Autowired
    private PCMemberRepoI repo;

    @Autowired
    private ReviewerRepoI reviewerRepo;

    @Autowired
    private ChairRepoI chairRepo;

    @Autowired
    private SectionRepoI sectionRepo;

    @Autowired
    private ListenerRepoI listenerRepo;

    @Autowired
    private LoginRepoI loginRepo;


    public boolean addPCMember(PCMember member) {
       if(member.getName().equals("") || member.getAffiliation().equals("") || member.getEmail().equals("") || member.getWebsite().equals("")|| loginRepo.findByUsername(member.getLogin().getUsername()) == null)
           return false;

       Optional<PCMember> mem = this.repo.findById(member.getId());
       if(mem.isPresent()) {
           return false;
       }
       this.repo.save(member);
       return true;
    }

    public Optional<PCMember> findOnePCMember(Integer id) {
        return repo.findById(id);
    }

    public boolean deletePCMember(Integer id) {
        Optional<PCMember> mem = this.repo.findById(id);

        if (mem.isPresent()){
            this.repo.deleteById(id);
            return true;
        }

        return false;
    }

    @Transactional
    public boolean updatePCMember(PCMember member){
        if(member.getName().equals("") || member.getAffiliation().equals("") || member.getEmail().equals("") || member.getWebsite().equals("")
            || loginRepo.findByUsername(member.getLogin().getUsername()) == null)
            return false;

        this.repo.findById(member.getId()).ifPresent(m -> {
            m.setName(member.getName());
            m.setAffiliation(member.getAffiliation());
            m.setEmail(member.getEmail());
            m.setWebsite(member.getWebsite());
        });
        return true;
    }

    public boolean addReviewer(Reviewer reviewer) {
        if(reviewer.getDomainOfInterest().equals(""))
            return false;

        Optional<Reviewer> re = this.reviewerRepo.findById(reviewer.getId());
        if(re.isPresent()) {
            return false;
        }
        this.reviewerRepo.save(reviewer);
        return true;
    }

    public Optional<Reviewer> getReviewer(int participantId) {
        Optional<Reviewer> p = reviewerRepo.findById(participantId);
        if (p.isPresent())
            return reviewerRepo.findById(participantId);
        else
            return Optional.empty();
    }

    public Optional<Reviewer> findOneReviewer(Integer id) {
        return reviewerRepo.findById(id);
    }

    public boolean deleteReviewer(Integer id) {
        Optional<Reviewer> rew = this.reviewerRepo.findById(id);

        if (rew.isPresent()){
            this.reviewerRepo.deleteById(id);
            return true;
        }

        return false;
    }

    @Transactional
    public boolean updateReviewer(Reviewer reviewer){
        if(reviewer.getDomainOfInterest().equals(""))
            return false;

        this.reviewerRepo.findById(reviewer.getId()).ifPresent(r -> {
           r.setDomainOfInterest(reviewer.getDomainOfInterest());
        });
        return true;
    }

    public boolean addChair(Chair chair) {
        if(chair.getPosition().equals(""))
            return false;

        Optional<Chair> p = chairRepo.findById(chair.getId());
        if (p.isPresent())
            return false;
        chairRepo.save(chair);
        return true;
    }

    public Optional<Chair> getChair(int participantId) {
        Optional<Chair> p = chairRepo.findById(participantId);
        if (p.isPresent())
            return chairRepo.findById(participantId);
        else
            return Optional.empty();
    }

    /*public Optional<Chair> getChairBySectionID(int sectionId) {
        Optional<Integer> chairId = sectionRepo.findAll().stream().filter(s -> s.getId() == sectionId).map(s-> s.getSectionChair().getId()).findAny();
        if (chairId.isPresent()) {
            return chairRepo.findById(chairId.get());
        } else
            return Optional.empty();
    }*/

    public boolean deleteChair(Integer id) {
        Optional<Chair> cha = this.chairRepo.findById(id);

        if (cha.isPresent()){
            this.reviewerRepo.deleteById(id);
            return true;
        }

        return false;
    }

    @Transactional
    public boolean updateChair(Chair chair){
        if(chair.getPosition().equals(""))
            return false;

        this.chairRepo.findById(chair.getId()).ifPresent(c -> {
            c.setPosition(chair.getPosition());
        });
        return true;
    }

    public Optional<Chair> findOneChair(Integer id) {
        return chairRepo.findById(id);
    }

    public Optional<PCMember> getPCMemberByUsername(String username) {
        Optional<Login> login = loginRepo.findAll().stream().filter(l -> l.getUsername().equals(username)).findAny();
        Optional<PCMember> pcMember = repo.findAll().stream().filter(pc -> pc.getLogin().equals(login.get())).findAny();
        if(pcMember.isPresent()) {
            return pcMember;
        }
        return null;
    }

    public List<PCMember> getAllPCMembers() {
        return repo.findAll();
    }

    public List<Reviewer> getAllReviewers() {
        return reviewerRepo.findAll();
    }

    public List<Chair> getAllChairs(){
        return chairRepo.findAll();
    }

    @Transactional
    public boolean addSectionToListener(Integer listenerID, Section s) {
        if(listenerRepo.findById(listenerID).isPresent()){
            Listener listener = listenerRepo.findById(listenerID).get();
            // List<Section> sections = listener.getSections();
            Section se = sectionRepo.findById(s.getId()).get();
            // sections.add(se);
            listener.setSectionId(se);
            this.listenerRepo.save(listener);
            return true;
        }
        return false;

    }

    @Transactional
    public long numberListenersPerSection(Integer sectionID) {
        if(sectionRepo.findById(sectionID).isPresent()){
            Section s = sectionRepo.findById(sectionID).get();
            List<Listener> listeners = listenerRepo.findAll();
            return listeners.stream().filter(l -> l.getSectionId().equals(s)).count();
        }

        return (long)(0);
    }
}


