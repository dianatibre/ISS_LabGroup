package ro.ubb.catalog.core.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Builder
@Entity
public class Email {

    @Id
    Integer id;

    String email;
    String token;
}
