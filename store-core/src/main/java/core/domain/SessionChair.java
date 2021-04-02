package core.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
public class SessionChair extends BaseEntity<Integer>{
    private String firstname;
    private String lastname;
    private Integer age;

    @OneToOne(mappedBy = "sessionChair")
    private Section section;
}
