package ro.ubb.catalog.core.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    private SessionChair sessionChair;

    @ManyToOne(cascade = CascadeType.ALL)
    private Conference conference;

    @OneToOne(mappedBy = "section",cascade = CascadeType.ALL)
    private Speaker speaker;
}
