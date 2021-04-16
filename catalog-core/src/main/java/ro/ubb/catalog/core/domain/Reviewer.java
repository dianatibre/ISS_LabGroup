package ro.ubb.catalog.core.domain;

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
public class Reviewer extends PCMember {
    private String domainOfInterest;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Bidding> biddings;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Evaluation> evaluations;
}
