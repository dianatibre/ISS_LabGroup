package domain;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@Builder
public class Proposal extends BaseEntity<Integer> {
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    private String title;
    private String description;
    private String abstractProposal;

    @OneToOne(cascade = CascadeType.ALL)
    private Paper paper;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
    List<Bidding> biddings;
}
