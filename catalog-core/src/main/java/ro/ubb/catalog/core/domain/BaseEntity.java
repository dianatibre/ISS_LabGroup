package ro.ubb.catalog.core.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@NoArgsConstructor
@Data
public class BaseEntity<ID extends Serializable> implements Serializable {
    @Id
    private ID id;

    @Override
    public String toString() {
        return "ID: " + id + " - ";
    }
}
