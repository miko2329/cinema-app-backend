package kz.meirbek.cinemaappcountryservice.repository;

import kz.meirbek.cinemaappcountryservice.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> getCountryByName(String name);

    Optional<Country> getCountryByIsoCode(String isoCode);
}
