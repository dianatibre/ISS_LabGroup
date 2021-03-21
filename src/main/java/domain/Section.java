package domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public class Section extends BaseEntity<Integer> {
    private String name;

//    private int conferenceID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sectionChairId")
    private SectionChair sectionChair;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conferenceId")
    private Conference conference;
}
