package domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Entity //represents a table in a relational database
@Builder
public class Login {
    @Id @Column(name = "username")
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "participantId")
    private Participant participant;
//    public void get(){
//        Login login = Login.builder().username("a").password("a").build();
//        login.getPassword();
//    }
}
