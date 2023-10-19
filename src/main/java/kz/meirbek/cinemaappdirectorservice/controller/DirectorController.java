package kz.meirbek.cinemaappdirectorservice.controller;

import kz.meirbek.cinemaappdirectorservice.dto.DirectorResponse;
import kz.meirbek.cinemaappdirectorservice.dto.Message;
import kz.meirbek.cinemaappdirectorservice.entity.Director;
import kz.meirbek.cinemaappdirectorservice.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/director")
public class DirectorController {

    private DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DirectorResponse>> getAll() {
        return directorService.getAll();
    }

    @GetMapping("/get")
    public ResponseEntity<DirectorResponse> getByAttr(@RequestParam String attr, @RequestParam String value) {
        return directorService.getByAttr(attr, value);
    }

    @PutMapping("/add")
    public ResponseEntity<Message> add(@RequestBody Director director) {
        return directorService.add(director);
    }

    @PatchMapping("/update")
    public ResponseEntity<Message> update(@RequestBody Director director) {
        return directorService.update(director);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        return directorService.delete(id);
    }
}
