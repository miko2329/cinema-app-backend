package kz.meirbek.cinemaappactorservice.controller;

import kz.meirbek.cinemaappactorservice.dto.ActorResponse;
import kz.meirbek.cinemaappactorservice.dto.Message;
import kz.meirbek.cinemaappactorservice.entity.Actor;
import kz.meirbek.cinemaappactorservice.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/actor")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ActorResponse>> getAll() {
        return actorService.getAll();
    }

    @GetMapping("/get")
    public ResponseEntity<ActorResponse> getByAttr(@RequestParam String attr, @RequestParam String value) {
        return actorService.getByAttr(attr, value);
    }

    @PutMapping("/add")
    public ResponseEntity<Message> add(@RequestBody Actor actor) {
        return actorService.add(actor);
    }

    @PatchMapping("/update")
    public ResponseEntity<Message> update(@RequestBody Actor actor) {
        return actorService.update(actor);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        return actorService.delete(id);
    }


//    @PutMapping("/addWithCountry")
//    public ResponseEntity<Message> addWithCountry(@RequestBody ActorWithCountry actorWithCountry) {
//        return actorService.addWithCountry(actorWithCountry);
//    }
//
//    @PatchMapping("/updateWithCountry")
//    public ResponseEntity<Message> updateWithCountry(@RequestBody ActorWithCountry actorWithCountry) {
//        return actorService.addWithCountry(actorWithCountry);
//    }

}
