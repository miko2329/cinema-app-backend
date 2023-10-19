package kz.meirbek.cinemaappdirectorservice.service;

import kz.meirbek.cinemaappdirectorservice.dto.DirectorResponse;
import kz.meirbek.cinemaappdirectorservice.dto.Message;
import kz.meirbek.cinemaappdirectorservice.entity.Director;
import kz.meirbek.cinemaappdirectorservice.feign.ICountryFeign;
import kz.meirbek.cinemaappdirectorservice.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectorService {

    private DirectorRepository directorRepository;
    private ICountryFeign countryFeign;

    @Autowired
    public DirectorService(DirectorRepository directorRepository, ICountryFeign countryFeign) {
        this.directorRepository = directorRepository;
        this.countryFeign = countryFeign;
    }

    public ResponseEntity<List<DirectorResponse>> getAll() {
        try {
            List<Director> directors = directorRepository.findAll();
            List<DirectorResponse> directorResponses = new ArrayList<>();

            for (Director director: directors) {
                DirectorResponse directorResponse = DirectorResponse.mapToDirectorResponse(director);
                directorResponse.setCountry(countryFeign.getByAttr("id", String.valueOf(director.getCountryId())).getBody());
                directorResponses.add(directorResponse);
            }

            return new ResponseEntity<>(directorResponses, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<DirectorResponse> getByAttr(String attr, String value) {
        try {
            DirectorResponse directorResponse = null;

            if(attr.equals("id")){
                Long id = Long.valueOf(value);
                Director director = directorRepository.findById(id).get();
                directorResponse = DirectorResponse.mapToDirectorResponse(director);
                directorResponse.setCountry(countryFeign.getByAttr("id", String.valueOf(director.getCountryId())).getBody());
            }
//            else if(attr.equals("firstname")){
//                return new ResponseEntity<>(actorRepository.getActorsByFirstName(value).get(), HttpStatus.OK);
//            }
            else if(attr.equals("fullname")){
                String[] names = value.split("_", 2);
                Director director = directorRepository.getDirectorByFirstNameAndLastName(names[0], names[1]).get();
                directorResponse = DirectorResponse.mapToDirectorResponse(director);
                directorResponse.setCountry(countryFeign.getByAttr("id", String.valueOf(director.getCountryId())).getBody());
            }
            else{
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(directorResponse, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> add(Director director) {
        try {
            directorRepository.save(director);
            return new ResponseEntity<>(new Message("Successfully added"), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Add failed"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> update(Director director) {
        try {
            boolean isExist = directorRepository.existsById(director.getId());

            if(isExist) {
                directorRepository.save(director);
                return new ResponseEntity<>(new Message("Successfully updated"), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new Message("Director with given id doesn't exist"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Update failed"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> delete(Long id) {
        try {
            directorRepository.deleteById(id);
            return new ResponseEntity<>(new Message("Successfully deleted"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Delete failed"), HttpStatus.BAD_REQUEST);
    }
}
