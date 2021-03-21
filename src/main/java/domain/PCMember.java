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
@Inheritance(strategy = InheritanceType.JOINED)
public class PCMember extends BaseEntity<Integer> {

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Conference> conferences;

    private String name;
    private String affiliation;
    private String email;
    private String website;

    @OneToOne
    @JoinColumn(name = "loginId")
    private Login login;
}