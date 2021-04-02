package domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "sessionchair", schema = "public", catalog = "cms")
public class SessionChair extends BaseEntity<Integer>{
    private String firstname;
    private String lastname;
    private Integer age;

    @OneToOne(mappedBy = "sessionChair")
    private Section section;
}
