package ro.ubb.catalog.core.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Builder
@Entity
public class Login {

    @Id
    private String username;
    private String password;

    @OneToOne(mappedBy = "login")
    private Participant participant;

    @OneToOne(mappedBy = "login")
    private PCMember pcMember;
}
