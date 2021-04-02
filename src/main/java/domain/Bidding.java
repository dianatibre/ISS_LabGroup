package domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Bidding extends BaseEntity<Integer>{
    private String result;

    @ManyToOne
    @JoinColumn(name = "reviewerId")
    private Reviewer reviewer;

    @ManyToOne
    @JoinColumn(name = "proposalId")
    private Proposal proposal;
}
