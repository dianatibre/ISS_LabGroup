package core.domain;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity //represents a table in a relational database
@NoArgsConstructor //default constructor
@AllArgsConstructor
//Generates an all-args constructor. An all-args constructor requires one argument for every field in the class.
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public class Paper extends BaseEntity<Integer>{
    private String title;
    private String document;

    @OneToOne(cascade = CascadeType.ALL)
    private Proposal proposal;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL)
    List<Evaluation> evaluations;

    @OneToOne(mappedBy = "paper",cascade = CascadeType.ALL)
    private Speaker speaker;
}
