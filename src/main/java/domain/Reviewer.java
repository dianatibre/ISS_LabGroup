package domain;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Reviewer extends PCMember{
    private String domainOfInterest;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    List<Bidding> biddings;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    List<Evaluation> evaluations;
}
