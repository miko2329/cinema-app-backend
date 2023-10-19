package kz.meirbek.cinemaappdirectorservice.feign;

import kz.meirbek.cinemaappdirectorservice.entity.Country;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("COUNTRY-SERVICE")
public interface ICountryFeign {
    @GetMapping("api/v1/country/get")
    public ResponseEntity<Country> getByAttr(@RequestParam String attr, @RequestParam String value);
}
