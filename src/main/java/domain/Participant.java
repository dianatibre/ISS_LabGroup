package domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
@MappedSuperclass
public class Participant extends BaseEntity<Integer>{
    @OneToMany(mappedBy = "participant",fetch = FetchType.EAGER) //loaded together with the rest of the fields
    List<Fee> fees;

    private String firstName;
    private String lastName;
    private int age;

    @OneToMany(mappedBy="participant")
    private Login login;
}
