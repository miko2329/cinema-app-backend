package kz.meirbek.personservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Date deathDate;
    private String imageUrl;

    private Integer countryId;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
}
