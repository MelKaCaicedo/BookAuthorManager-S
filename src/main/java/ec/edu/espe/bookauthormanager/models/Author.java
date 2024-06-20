package ec.edu.espe.bookauthormanager.models;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String nationality;
    private String biography;
    private String website;

}
