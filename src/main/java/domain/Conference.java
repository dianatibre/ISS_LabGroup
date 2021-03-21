package domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
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
    private Date startDate;
    private Date endDate;
    private int capacity;
    private Date paperDeadline;
    private Date abstractDeadline;

    @OneToMany(mappedBy = "conference",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)//used for collections
    private List<Fee> fees;

    @OneToMany(mappedBy = "conference",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)//used for collections
    private List<Section> sections;

    @OneToMany(mappedBy = "conference",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)//used for collections
    private List<Proposal> proposals;

    @ManyToMany(mappedBy = "conference",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PCMember> pcMembers;
}
