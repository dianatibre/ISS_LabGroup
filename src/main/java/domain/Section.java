package domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

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
    private SectionChair sectionChair;
}
