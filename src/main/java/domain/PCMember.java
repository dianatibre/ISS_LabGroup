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
public class PCMember extends BaseEntity<Integer>{
    private String name;
    private String affiliation;
    private String email;
    private String website;

    @OneToMany(mappedBy = "pcMember",cascade = CascadeType.ALL)
    private List<PCMember_Conference> conferences;

    @OneToMany(mappedBy = "pcMember")
    private Login login;
}
