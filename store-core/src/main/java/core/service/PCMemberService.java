package core.service;

import core.domain.*;

import core.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
       if(member.getName().equals("") || member.getConferences().isEmpty() || member.getLogin().isEmpty())
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

    public boolean addReviewer(Reviewer reviewer) {
        if(reviewer.getName().equals("") || reviewer.getBiddings().isEmpty() )
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

    public boolean addChair(Chair chair) {
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
/*
    public Optional<Chair> getChairBySectionID(int sectionId) {
        Optional<Integer> chairId = sectionRepo.findAll().stream().filter(s -> s.getId() == sectionId).map(s-> s.getSectionChair().getId()).findAny();
        if (chairId.isPresent()) {
            return chairRepo.findById(chairId.get());
        } else
            return Optional.empty();
    }*/

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

    @Transactional
    public boolean addSectionToListener(Integer listenerID, Section s) {
        Listener listener = listenerRepo.findById(listenerID).get();
       // List<Section> sections = listener.getSections();
        Section se = sectionRepo.findById(s.getId()).get();
       // sections.add(se);
        listener.setSectionId(se);
        this.listenerRepo.save(listener);
        return true;
    }

    @Transactional
    public long numberListenersPerSection(Integer sectionID) {
        Section s = sectionRepo.findById(sectionID).get();
        List<Listener> listeners = listenerRepo.findAll();
        return listeners.stream().filter(l -> l.getSectionId().equals(s)).count();
    }
}


