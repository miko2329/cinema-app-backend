package kz.meirbek.cinemaappactorservice.repository;

import kz.meirbek.cinemaappactorservice.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Optional<Actor> getActorByFirstNameAndLastName(String firstName, String lastName);

    Optional<Actor> getActorsByFirstName(String firstName);
}
