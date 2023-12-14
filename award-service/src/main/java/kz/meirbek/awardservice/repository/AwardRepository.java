package kz.meirbek.awardservice.repository;

import kz.meirbek.awardservice.entity.Award;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AwardRepository extends JpaRepository<Award, Integer> {
    Optional<Award> findAwardByName(String name);
}
