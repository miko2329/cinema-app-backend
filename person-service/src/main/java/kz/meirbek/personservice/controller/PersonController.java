package kz.meirbek.personservice.controller;

import kz.meirbek.personservice.dto.Message;
import kz.meirbek.personservice.dto.PersonResponse;
import kz.meirbek.personservice.entity.Person;
import kz.meirbek.personservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonResponse>> getAll(@RequestParam(required = false) String activity) {
        return personService.getAll(activity);
    }

    @GetMapping("/get")
    public ResponseEntity<PersonResponse> getByAttr(@RequestParam String attr, @RequestParam String value) {
        return personService.getByAttr(attr, value);
    }

    @PutMapping("/add")
    public ResponseEntity<Message> add(@RequestBody Person person) {
        return personService.add(person);
    }

    @PatchMapping("/update")
    public ResponseEntity<Message> update(@RequestBody Person person) {
        return personService.update(person);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        return personService.delete(id);
    }
}
