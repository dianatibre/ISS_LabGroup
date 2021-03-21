package domain;

import lombok.*;
import domain.Proposal;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Inheritance()
public class Author extends Participant{

    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)//used for collections
    private List<Proposal> proposal;

}
