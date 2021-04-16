package ro.ubb.catalog.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedLogin {
    private String role;
    private Participant participant;
}