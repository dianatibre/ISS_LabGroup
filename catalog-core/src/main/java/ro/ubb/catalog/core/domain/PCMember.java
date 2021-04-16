package ro.ubb.catalog.core.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

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

    @OneToOne
    private Login login;
}
