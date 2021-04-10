package core.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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

    @OneToOne
    private Login login;
}
