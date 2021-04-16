package ro.ubb.catalog.core.domain;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class Listener extends Participant {
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne
    private Section sectionId;
}
