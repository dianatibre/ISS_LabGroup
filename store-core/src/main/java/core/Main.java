package core;

import core.domain.Chair;
import core.domain.Conference;
import core.repository.ChairRepoI;
import core.service.ConferenceService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("core.config");

//        ChairRepoI chairRepoI=context.getBean(ChairRepoI.class);
//        Chair chair=new Chair("abc");
//       chairRepoI.save(chair);
//        chairRepoI.deleteById(7);

//        Date date=new Date(2020/10/10);
//        Conference conference=new Conference(date,date,date,1,date,"a",date,date,date,"a","a",date,date,date,"a",date);
//        conference.setId(1);
//        ConferenceService conferenceService=context.getBean(ConferenceService.class);
//       conferenceService.addConference(conference);
//        conferenceService.deleteConference(8);

    }
}
