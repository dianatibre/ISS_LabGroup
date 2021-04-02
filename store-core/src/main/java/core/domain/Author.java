package core.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
