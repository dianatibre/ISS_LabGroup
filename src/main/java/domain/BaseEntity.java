package domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@Data
public class BaseEntity<ID> {
    @Id
    @GeneratedValue
    private ID id;

    @Override
    public String toString() {
        return "ID: " + id + " - ";
    }
}
