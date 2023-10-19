package kz.meirbek.cinemaappcountryservice.controller;


import kz.meirbek.cinemaappcountryservice.dto.Message;
import kz.meirbek.cinemaappcountryservice.entity.Country;
import kz.meirbek.cinemaappcountryservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Country>> getAll() {
        return countryService.getAll();
    }

    @GetMapping("/get")
    public ResponseEntity<Country> getByAttr(@RequestParam String attr, @RequestParam String value) {
        return countryService.getByAttr(attr, value);
    }

    @PutMapping("/add")
    public ResponseEntity<Message> add(@RequestBody Country country) {
        return countryService.add(country);
    }

    @PatchMapping("/update")
    public ResponseEntity<Message> update(@RequestBody Country country) {
        return countryService.update(country);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable Integer id) {
        return countryService.delete(id);
    }
}
