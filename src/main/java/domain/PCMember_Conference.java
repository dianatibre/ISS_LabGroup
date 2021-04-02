package domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Builder
@Entity
public class PCMember_Conference {
    @ManyToOne
    @JoinColumn(columnDefinition = "pcMemberId")
    private PCMember pcMember;

    @ManyToOne
    @JoinColumn(columnDefinition = "conferenceId")
    private Conference conference;
}
