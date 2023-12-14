package kz.meirbek.personservice.dto;

import kz.meirbek.personservice.entity.Activity;
import kz.meirbek.personservice.entity.Country;
import kz.meirbek.personservice.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Date deathDate;
    private String imageUrl;

    private Country country;

    private Activity activity;

    public static Person mapToPerson(PersonResponse personResponse) {
        return new Person(personResponse.getId(),
                personResponse.getFirstName(),
                personResponse.getLastName(),
                personResponse.getBirthDate(),
                personResponse.getDeathDate(),
                personResponse.getImageUrl(),
                personResponse.getCountry().getId(),
                personResponse.getActivity());
    }

    public static PersonResponse mapToPersonResponse(Person person) {
        PersonResponse personResponse = new PersonResponse();

        personResponse.setId(person.getId());
        personResponse.setFirstName(person.getFirstName());
        personResponse.setLastName(person.getLastName());
        personResponse.setBirthDate(person.getBirthDate());
        personResponse.setDeathDate(person.getDeathDate());
        personResponse.setImageUrl(person.getImageUrl());
        personResponse.setActivity(person.getActivity());

        return personResponse;
    }
}
