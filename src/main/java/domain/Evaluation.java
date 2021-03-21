package domain;

import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Evaluation extends BaseEntity<Integer>{
    @ManyToOne
    @JoinColumn(name = "reviewerId")
    private Reviewer reviewer;

    @ManyToOne
    @JoinColumn(name = "paperId")
    private Paper paper;

    private String result;
}
