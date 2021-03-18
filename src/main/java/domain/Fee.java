package domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Fee extends BaseEntity<Integer>{
    @ManyToOne
    @JoinColumn(name="conferenceId")
    private Conference conference;

    @ManyToOne
    @JoinColumn(name="participantId")
    private Participant participant;

    private Date date;
}
