package kz.meirbek.personservice.feign;

import kz.meirbek.personservice.entity.Country;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("COUNTRY-SERVICE")
public interface ICountryFeign {
    @GetMapping("api/v1/countries/get")
    public ResponseEntity<Country> getByAttr(@RequestParam String attr, @RequestParam String value);

}
