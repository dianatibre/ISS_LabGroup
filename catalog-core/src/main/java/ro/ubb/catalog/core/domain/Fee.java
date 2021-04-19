package ro.ubb.catalog.core.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@ToString
@Builder
public class Fee extends BaseEntity<Integer>{
    @ManyToOne
    private Conference conference;

    @ManyToOne
    private Participant participant;

    private Date date;
}
