package domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Speaker extends Author{
    private String presentation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sectionId")
    private Section section;

    @OneToOne
    @JoinColumn(name="paperId")
    private Paper paper;
}
