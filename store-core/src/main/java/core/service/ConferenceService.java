package core.service;

import core.domain.Conference;
import core.repository.ConferenceRepoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepoI conferenceRepoI;

    public Optional<Conference> findOneConference(Integer id) {
        return conferenceRepoI.findById(id);
    }

    public List<Conference> getConferences() {
        return new ArrayList<>(this.conferenceRepoI.findAll());
    }

    public boolean addConference(Conference conference) {
        if(conference.getName().equals("") || conference.getTopic().equals("") || conference.getDescription().equals("")||
            conference.getLocation().equals("") || (conference.getStartdate().compareTo(conference.getEnddate()))<0 || conference.getCapacity()<=0){
            return false;
        }
        Optional<Conference> conf = this.conferenceRepoI.findById(conference.getId());
        if (conf.isPresent()) {
            return false;
        }
        this.conferenceRepoI.save(conference);
        return true;
    }

    public boolean deleteConference(Integer id) {
        Optional<Conference> conf = this.conferenceRepoI.findById(id);
        if (conf.isPresent()) {
            this.conferenceRepoI.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateConference(Conference conference){
        if(conference.getName().equals("") || conference.getTopic().equals("") || conference.getDescription().equals("")||
                conference.getLocation().equals("") || (conference.getStartdate().compareTo(conference.getEnddate()))<0 || conference.getCapacity()<=0){
            return false;
        }
        this.conferenceRepoI.findById(conference.getId()).ifPresent(c->{
            c.setName(conference.getName());
            c.setTopic(conference.getTopic());
            c.setDescription(conference.getDescription());
            c.setLocation(conference.getLocation());
            c.setStartdate(conference.getStartdate());
            c.setEnddate(conference.getEnddate());
            c.setCapacity(conference.getCapacity());
            c.setPaperDeadline(conference.getPaperDeadline());
            c.setAbstractDeadline(conference.getAbstractDeadline());
            c.setBiddingDeadline(conference.getBiddingDeadline());
            c.setAssignReviewers(conference.getAssignReviewers());
            c.setReview(conference.getReview());
            c.setImprovePaperDeadline(conference.getImprovePaperDeadline());
            c.setSections(conference.getSections());
            c.setUploadPresentationDeadline(conference.getUploadPresentationDeadline());
            c.setListenerRegistrationDeadline(conference.getListenerRegistrationDeadline());
        });
        return true;
    }
}
