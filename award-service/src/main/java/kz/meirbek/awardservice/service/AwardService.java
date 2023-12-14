package kz.meirbek.awardservice.service;

import kz.meirbek.awardservice.dto.Message;
import kz.meirbek.awardservice.entity.Award;
import kz.meirbek.awardservice.repository.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardService {

    private AwardRepository awardRepository;

    @Autowired
    public AwardService(AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
    }

    public ResponseEntity<List<Award>> getAll() {
        try {
            return new ResponseEntity<>(awardRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Award> getByAttr(String attr, String value) {
        try {
            Award award = null;

            if(attr.equals("id")){
                Integer id = Integer.valueOf(value);
                award = awardRepository.findById(id).get();
            }
            else if(attr.equals("name")){
                award = awardRepository.findAwardByName(value).get();
            }
            else{
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(award, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> add(Award award) {
        try {
            awardRepository.save(award);
            return new ResponseEntity<>(new Message("Successfully added"), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Add failed"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> update(Award award) {
        try {
            boolean isExist = awardRepository.existsById(award.getId());

            if(isExist) {
                awardRepository.save(award);
                return new ResponseEntity<>(new Message("Successfully updated"), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new Message("Award with given id doesn't exist"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Update failed"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> delete(Integer id) {
        try {
            awardRepository.deleteById(id);
            return new ResponseEntity<>(new Message("Successfully deleted"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Delete failed"), HttpStatus.BAD_REQUEST);
    }
}
