package ro.ubb.catalog.core.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@ToString
@Builder
public class Evaluation extends BaseEntity<Integer>{
    private String result;
    private String recommendation;
    @ManyToOne
    private Reviewer reviewer;

    @ManyToOne
    private Paper paper;
}
