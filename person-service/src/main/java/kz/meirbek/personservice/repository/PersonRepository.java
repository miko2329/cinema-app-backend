package kz.meirbek.personservice.repository;

import kz.meirbek.personservice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findPersonByFirstNameAndLastName(String firstName, String lastName);

    List<Person> findAllByActivity_Name(String name);
}
