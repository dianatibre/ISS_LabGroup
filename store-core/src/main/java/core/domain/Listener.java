package core.domain;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class Listener extends Participant {
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "listener",cascade = CascadeType.ALL)
    private List<Section> sections = new ArrayList<>();
}
