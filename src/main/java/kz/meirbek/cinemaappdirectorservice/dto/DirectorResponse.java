package kz.meirbek.cinemaappdirectorservice.dto;

import kz.meirbek.cinemaappdirectorservice.entity.Country;
import kz.meirbek.cinemaappdirectorservice.entity.Director;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Date deathDate;

    private Country country;

    public static Director mapToActor(DirectorResponse directorResponse) {
        return new Director(directorResponse.getId(),
                directorResponse.getFirstName(),
                directorResponse.getLastName(),
                directorResponse.getBirthDate(),
                directorResponse.getDeathDate(),
                directorResponse.getCountry().getId());
    }

    public static DirectorResponse mapToDirectorResponse(Director director) {
        DirectorResponse directorResponse = new DirectorResponse();

        directorResponse.setId(director.getId());
        directorResponse.setFirstName(director.getFirstName());
        directorResponse.setLastName(director.getLastName());
        directorResponse.setBirthDate(director.getBirthDate());
        directorResponse.setDeathDate(director.getDeathDate());

        return directorResponse;
    }
}
