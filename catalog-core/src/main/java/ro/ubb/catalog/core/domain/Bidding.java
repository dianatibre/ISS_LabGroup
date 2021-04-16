package ro.ubb.catalog.core.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Bidding extends BaseEntity<Integer>{
    private String result;

    @ManyToOne
    private Reviewer reviewer;

    @ManyToOne
    private Proposal proposal;
}
