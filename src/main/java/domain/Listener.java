package domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class Listener extends Participant{
    @OneToMany(mappedBy = "listener",cascade = CascadeType.ALL)
    private List<Section> sections = new ArrayList<>();
}
