package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.domain.Section;
import ro.ubb.catalog.core.repository.ConferenceRepoI;
import ro.ubb.catalog.core.repository.SectionRepoI;
import ro.ubb.catalog.core.repository.SessionChairRepoI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {
    @Autowired
    private SectionRepoI sectionRepo;
    @Autowired
    private ConferenceRepoI conferenceRepo;
    @Autowired
    private SessionChairRepoI sessionChairRepo;

    public Optional<Section> findOneSection(Integer id) {
        return sectionRepo.findById(id);
    }

    public List<Section> getSections() {
        return new ArrayList<>(this.sectionRepo.findAll());
    }

    public boolean addSection(Section section) {
        if (section.getName().equals("") || !conferenceRepo.findById(section.getConference().getId()).isPresent()
                || !sessionChairRepo.findById(section.getSessionChair().getId()).isPresent())
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
    public boolean updateSection(Section section) {
        if (section.getName().equals("") || !conferenceRepo.findById(section.getConference().getId()).isPresent()
                || !sessionChairRepo.findById(section.getSessionChair().getId()).isPresent())
            return false;
        this.sectionRepo.findById(section.getId()).ifPresent(s -> {
            s.setName(section.getName());
            s.setConference(section.getConference());
            s.setSessionChair(section.getSessionChair());
            s.setSpeaker(section.getSpeaker());
        });
        return true;
    }
}
