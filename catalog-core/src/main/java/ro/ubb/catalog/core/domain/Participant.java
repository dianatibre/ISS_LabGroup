package ro.ubb.catalog.core.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Participant extends BaseEntity<Integer>{
    @OneToMany(mappedBy = "participant", fetch = FetchType.EAGER)//loaded together with the rest of the fields
            List<Fee> fees;

    private String firstName;
    private String lastName;
    private int age;
    private String affiliation;

    @OneToOne
    private Login login;
}
