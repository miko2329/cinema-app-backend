package kz.meirbek.countryservice.repository;

import kz.meirbek.countryservice.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findCountryByName(String name);

    Optional<Country> findCountryByIsoCode(String isoCode);
}
