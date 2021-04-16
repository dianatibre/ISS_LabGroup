package ro.ubb.catalog.core.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class Author extends Participant {

    @ManyToOne
    @JoinColumn(columnDefinition = "proposalId")
    private Proposal proposal;
}
