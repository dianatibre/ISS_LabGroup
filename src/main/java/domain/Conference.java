package domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public class Conference extends BaseEntity<Integer>{
    private String name;
    private String topic;
    private String description;
    private String location;
    private Date startdate;
    private Date enddate;
    private Integer capacity;
    private Date paperDeadline;
    private Date abstractDeadline;
    private Date biddingDeadline;
    private Date assignReviewers;
    private Date review;
    private Date improvePaperDeadline;
    private Date createSection;
    private Date uploadPresentationDeadline;
    private Date listenerRegistrationDeadline;

    @OneToMany(mappedBy = "conference",cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)//used for collections
    private List<Fee> fees;

    @OneToMany(mappedBy = "conference",cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)//used for collections
    private List<Section> sections;

    @OneToMany(mappedBy = "conference",cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)//used for collections
    private List<Proposal> proposals;

    @OneToMany(mappedBy = "conference",cascade = CascadeType.ALL)
    private List<PCMember_Conference> conferences;
}
