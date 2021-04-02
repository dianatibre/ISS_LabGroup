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
public class Reviewer extends PCMember{
    private String domainOfInterest;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    List<Bidding> biddings;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    List<Evaluation> evaluations;
}
