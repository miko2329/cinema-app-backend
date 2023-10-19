package kz.meirbek.cinemaappawardservice.repository;

import kz.meirbek.cinemaappawardservice.entity.Award;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AwardRepository extends JpaRepository<Award, Integer> {
    Optional<Award> getAwardByName(String name);
}
