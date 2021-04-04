package core.service;

import core.domain.Section;
import core.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SectionService {
    @Autowired
    private SectionRepoI sectionRepo;
    @Autowired
    private ConferenceRepoI conferenceRepo;
    @Autowired
    private SessionChairRepoI sessionChairRepo;
    @Autowired
    private SpeakerRepoI speakerRepo;
    @Autowired
    private ListenerRepoI listenerRepo;

    public Optional<Section> findOneSection(Integer id) { return sectionRepo.findById(id); }

    public List<Section> getSections() {
        return new ArrayList<>(this.sectionRepo.findAll());
    }

    public boolean addSection(Section section) {
        if(section.getName().equals("") || !conferenceRepo.findById(section.getConference().getId()).isPresent()
        || !sessionChairRepo.findById(section.getSessionChair().getId()).isPresent() ||
                !speakerRepo.findById(section.getSpeaker().getId()).isPresent())
            return false;
        Optional<Section> conf = this.sectionRepo.findById(section.getId());
        if (conf.isPresent()) {
            return false;
        }
        this.sectionRepo.save(section);
        return true;
    }

    public boolean deleteSection(Integer id) {
        Optional<Section> conf = this.sectionRepo.findById(id);
        if (conf.isPresent()) {
            this.sectionRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateSection(Section section){
        if(section.getName().equals("") || !conferenceRepo.findById(section.getConference().getId()).isPresent()
                || !sessionChairRepo.findById(section.getSessionChair().getId()).isPresent() ||
                !speakerRepo.findById(section.getSpeaker().getId()).isPresent())
            return false;
        this.sectionRepo.findById(section.getId()).ifPresent(s->{
           s.setName(section.getName());
           s.setConference(section.getConference());
           s.setSessionChair(section.getSessionChair());
           s.setSpeaker(section.getSpeaker());
        });
        return true;
    }

}
