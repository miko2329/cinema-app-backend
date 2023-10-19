package kz.meirbek.cinemaappcountryservice.service;

import kz.meirbek.cinemaappcountryservice.dto.Message;
import kz.meirbek.cinemaappcountryservice.entity.Country;
import kz.meirbek.cinemaappcountryservice.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public ResponseEntity<List<Country>> getAll() {
        try {
            return new ResponseEntity<>(countryRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Country> getByAttr(String attr, String value) {
        try {
            Country country = null;

            if(attr.equals("id")){
                Integer id = Integer.valueOf(value);
                country = countryRepository.findById(id).get();
            }
            else if(attr.equals("name")){
                country = countryRepository.getCountryByName(value).get();
            }
            else if (attr.equals("isocode")) {
                country = countryRepository.getCountryByIsoCode(value).get();
            }
            else{
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(country, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> add(Country country) {
        try {
            countryRepository.save(country);
            return new ResponseEntity<>(new Message("Successfully added"), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Add failed"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> update(Country country) {
        try {
            boolean isExist = countryRepository.existsById(country.getId());

            if(isExist) {
                countryRepository.save(country);
                return new ResponseEntity<>(new Message("Successfully updated"), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new Message("Country with given id doesn't exist"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Update failed"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> delete(Integer id) {
        try {
            countryRepository.deleteById(id);
            return new ResponseEntity<>(new Message("Successfully deleted"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Delete failed"), HttpStatus.BAD_REQUEST);
    }
}
