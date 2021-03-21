package domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Inheritance()
public class Author extends Participant{

    @OneToMany
    private Proposal proposal;
}
