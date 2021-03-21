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
public class Speaker extends Author {

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private Section section;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private Paper paper;

    @Getter @Setter
    private String presentation;
}
