package core.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Speaker extends Author{
    private String presentation;

    @OneToOne(cascade = CascadeType.ALL)
    private Section section;

    @OneToOne
    private Paper paper;
}
