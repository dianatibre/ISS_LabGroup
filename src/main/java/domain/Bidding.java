package domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Bidding extends BaseEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "reviewerId")
    private Reviewer reviewer;

    @ManyToOne
    @JoinColumn(name = "proposalId")
    private Proposal proposal;
    private String result;
}