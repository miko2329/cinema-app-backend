package kz.meirbek.cinemaappdirectorservice.repository;

import kz.meirbek.cinemaappdirectorservice.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    Optional<Director> getDirectorByFirstNameAndLastName(String firstName, String lastName);
}
