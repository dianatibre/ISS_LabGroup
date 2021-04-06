package core.domain;

import lombok.*;

import javax.persistence.*;

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
