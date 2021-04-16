package ro.ubb.catalog.core.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Conference extends BaseEntity<Integer> {
    private String name;
    private String topic;
    private String description;
    private String location;
    private String startdate;
    private String enddate;
    private Integer capacity;
    private String paperDeadline;
    private String abstractDeadline;
    private String biddingDeadline;
    private String assignReviewers;
    private String review;
    private String improvePaperDeadline;
    private String createSection;
    private String uploadPresentationDeadline;
    private String listenerRegistrationDeadline;
    private int tax;

    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)//used for collections
    private List<Fee> fees;

    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)//used for collections
    private List<Section> sections;

    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)//used for collections
    private List<Proposal> proposals;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PCMember> pcMembers;

//    public Conference(Date date, Date date1, Date date2, int i, Date date3, String a, Date date4, Date date5, Date date6, String a1, String a2, Date date7, Date date8, Date date9, String a3, Date date10) {
//        abstractDeadline=date;
//        assignReviewers=date1;
//        biddingDeadline=date2;
//        capacity=i;
//        createSection=date3;
//        description=a;
//        enddate=date4;
//        improvePaperDeadline=date5;
//        listenerRegistrationDeadline=date6;
//        location=a1;
//        name=a2;
//        paperDeadline=date7;
//        review=date8;
//        startdate=date9;
//        topic=a3;
//        uploadPresentationDeadline=date10;
//    }
}
