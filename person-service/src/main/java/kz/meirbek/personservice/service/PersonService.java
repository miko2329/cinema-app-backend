package kz.meirbek.personservice.service;

import kz.meirbek.personservice.dto.Message;
import kz.meirbek.personservice.dto.PersonResponse;
import kz.meirbek.personservice.entity.Person;
import kz.meirbek.personservice.feign.ICountryFeign;
import kz.meirbek.personservice.repository.PersonRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private ICountryFeign countryFeign;

    @Autowired
    public PersonService(PersonRepository personRepository, ICountryFeign countryFeign) {
        this.personRepository = personRepository;
        this.countryFeign = countryFeign;
    }

    public ResponseEntity<List<PersonResponse>> getAll(String activity) {
        try {

            List<Person> people = null;
            List<PersonResponse> personResponses = new ArrayList<>();

            if(StringUtils.isEmpty(activity)){
                people = personRepository.findAll();
            } else{
                people = personRepository.findAllByActivity_Name(activity);
            }

            for (Person person: people) {
                PersonResponse personResponse = PersonResponse.mapToPersonResponse(person);
                personResponse.setCountry(countryFeign.getByAttr("id", String.valueOf(person.getCountryId())).getBody());
                personResponses.add(personResponse);
            }

            if(personResponses.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(personResponses, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<PersonResponse> getByAttr(String attr, String value) {
        try {
            PersonResponse personResponse = null;

            if(attr.equals("id")){
                Long id = Long.valueOf(value);
                Person person = personRepository.findById(id).get();
                personResponse = PersonResponse.mapToPersonResponse(person);
                personResponse.setCountry(countryFeign.getByAttr("id", String.valueOf(person.getCountryId())).getBody());
            }
            else if(attr.equals("fullname")){
                String[] names = value.split("_", 2);
                Person person = personRepository.findPersonByFirstNameAndLastName(names[0], names[1]).get();
                personResponse = PersonResponse.mapToPersonResponse(person);
                personResponse.setCountry(countryFeign.getByAttr("id", String.valueOf(person.getCountryId())).getBody());
            }
            else{
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(personResponse, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof NoSuchElementException) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> add(Person person) {
        try {
            personRepository.save(person);
            return new ResponseEntity<>(new Message("Successfully added"), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Add failed"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> update(Person person) {
        try {
            boolean isExist = personRepository.existsById(person.getId());

            if(isExist) {
                personRepository.save(person);
                return new ResponseEntity<>(new Message("Successfully updated"), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new Message("Person with given id doesn't exist"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Update failed"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> delete(Long id) {
        try {
            personRepository.deleteById(id);
            return new ResponseEntity<>(new Message("Successfully deleted"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Delete failed"), HttpStatus.BAD_REQUEST);
    }
}
