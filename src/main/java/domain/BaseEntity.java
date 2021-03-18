package domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass //the mapping information is applied to the entities that inherit from it
@Data //getter+setter+toString()+equals()+hashCode()
@NoArgsConstructor //default constructor
public class BaseEntity<ID> {
    @Id
    @GeneratedValue//automatically generated id
    private ID id;

    @Override
    public String toString() {
        return "ID: " + id + " - ";
    }
}
