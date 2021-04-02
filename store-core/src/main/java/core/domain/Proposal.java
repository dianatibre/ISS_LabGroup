package core.domain;

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
public class Proposal extends BaseEntity<Integer>{
    private String title;
    private String keywords;
    private String topics;
    private String abstractProposal;

    @OneToOne(mappedBy = "proposal",cascade = CascadeType.ALL)
    private Paper paper;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
    List<Bidding> biddings;

    @ManyToOne(cascade = CascadeType.ALL)
    private Conference conference;

    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
    private List<Author> author;
}
