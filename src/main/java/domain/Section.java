package domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public class Section extends BaseEntity<Integer>{
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sectionChairId")
    private SessionChair sessionChair;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conferenceId")
    private Conference conference;

    @OneToOne(mappedBy = "section",cascade = CascadeType.ALL)
    private Speaker speaker;

    @ManyToOne
    @JoinColumn(name = "listenerId")
    private Listener listener;
}
