package kz.meirbek.cinemaappactorservice.dto;

import kz.meirbek.cinemaappactorservice.entity.Actor;
import kz.meirbek.cinemaappactorservice.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Date deathDate;

    private Country country;

    public static Actor mapToActor(ActorResponse actorResponse) {
        return new Actor(actorResponse.getId(),
                actorResponse.getFirstName(),
                actorResponse.getLastName(),
                actorResponse.getBirthDate(),
                actorResponse.getDeathDate(),
                actorResponse.getCountry().getId());
    }

    public static ActorResponse mapToActorResponse(Actor actor) {
        ActorResponse actorResponse = new ActorResponse();

        actorResponse.setId(actor.getId());
        actorResponse.setFirstName(actor.getFirstName());
        actorResponse.setLastName(actor.getLastName());
        actorResponse.setBirthDate(actor.getBirthDate());
        actorResponse.setDeathDate(actor.getDeathDate());

        return actorResponse;
    }
}
