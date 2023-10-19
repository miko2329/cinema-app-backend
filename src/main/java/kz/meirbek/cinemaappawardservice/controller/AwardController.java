package kz.meirbek.cinemaappawardservice.controller;

import kz.meirbek.cinemaappawardservice.dto.Message;
import kz.meirbek.cinemaappawardservice.entity.Award;
import kz.meirbek.cinemaappawardservice.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/award")
public class AwardController {

    private AwardService awardService;

    @Autowired
    public AwardController(AwardService awardService) {
        this.awardService = awardService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Award>> getAll() {
        return awardService.getAll();
    }

    @GetMapping("/get")
    public ResponseEntity<Award> getByAttr(@RequestParam String attr, @RequestParam String value) {
        return awardService.getByAttr(attr, value);
    }

    @PutMapping("/add")
    public ResponseEntity<Message> add(@RequestBody Award award) {
        return awardService.add(award);
    }

    @PatchMapping("/update")
    public ResponseEntity<Message> update(@RequestBody Award award) {
        return awardService.update(award);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable Integer id) {
        return awardService.delete(id);
    }
}
