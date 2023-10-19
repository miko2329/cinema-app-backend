package kz.meirbek.cinemaappactorservice.service;

import kz.meirbek.cinemaappactorservice.dto.ActorResponse;
import kz.meirbek.cinemaappactorservice.dto.Message;
import kz.meirbek.cinemaappactorservice.entity.Actor;
import kz.meirbek.cinemaappactorservice.feign.ICountryFeign;
import kz.meirbek.cinemaappactorservice.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService {
    private ActorRepository actorRepository;
    private ICountryFeign countryFeign;

    @Autowired
    public ActorService(ActorRepository actorRepository, ICountryFeign countryFeign) {
        this.actorRepository = actorRepository;
        this.countryFeign = countryFeign;
    }

    public ResponseEntity<List<ActorResponse>> getAll() {
        try {
            List<Actor> actors = actorRepository.findAll();
            List<ActorResponse> actorResponses = new ArrayList<>();

            for (Actor actor: actors) {
                ActorResponse actorResponse = ActorResponse.mapToActorResponse(actor);
                actorResponse.setCountry(countryFeign.getByAttr("id", String.valueOf(actor.getCountryId())).getBody());
                actorResponses.add(actorResponse);
            }

            return new ResponseEntity<>(actorResponses, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ActorResponse> getByAttr(String attr, String value) {
        try {
            ActorResponse actorResponse = null;

            if(attr.equals("id")){
                Long id = Long.valueOf(value);
                Actor actor = actorRepository.findById(id).get();
                actorResponse = ActorResponse.mapToActorResponse(actor);
                actorResponse.setCountry(countryFeign.getByAttr("id", String.valueOf(actor.getCountryId())).getBody());
            }
//            else if(attr.equals("firstname")){
//                return new ResponseEntity<>(actorRepository.getActorsByFirstName(value).get(), HttpStatus.OK);
//            }
            else if(attr.equals("fullname")){
                String[] names = value.split("_", 2);
                Actor actor = actorRepository.getActorByFirstNameAndLastName(names[0], names[1]).get();
                actorResponse = ActorResponse.mapToActorResponse(actor);
                actorResponse.setCountry(countryFeign.getByAttr("id", String.valueOf(actor.getCountryId())).getBody());
            }
            else{
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(actorResponse, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> add(Actor actor) {
        try {
            actorRepository.save(actor);
            return new ResponseEntity<>(new Message("Successfully added"), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Add failed"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> update(Actor actor) {
        try {
            boolean isExist = actorRepository.existsById(actor.getId());

            if(isExist) {
                actorRepository.save(actor);
                return new ResponseEntity<>(new Message("Successfully updated"), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new Message("Actor with given id doesn't exist"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Update failed"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> delete(Long id) {
        try {
            actorRepository.deleteById(id);
            return new ResponseEntity<>(new Message("Successfully deleted"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("Delete failed"), HttpStatus.BAD_REQUEST);
    }

//    public ResponseEntity<Message> addWithCountry(ActorWithCountry actorWithCountry) {
//        try {
//            Actor actor = ActorWithCountry.mapToActor(actorWithCountry);
//
//            if (actorWithCountry.getCountryAttr().equals("countryId")) {
//                Country country = countryRepository.findById(Integer.valueOf(actorWithCountry.getCountryAttrValue())).get();
////                Country country = new Country();
////                country.setId(Integer.valueOf(actorWithCountry.getCountryAttrValue()));
//                actor.setCountry(country);
//            } else if(actorWithCountry.getCountryAttr().equals("countryName")) {
//                Country country = countryRepository.getCountryByName(actorWithCountry.getCountryAttrValue()).get();
//                actor.setCountry(country);
//            } else if (actorWithCountry.getCountryAttr().equals("countryIsoCode")) {
//                Country country = countryRepository.getCountryByIsoCode(actorWithCountry.getCountryAttrValue()).get();
//                actor.setCountry(country);
//            }
//
//            actorRepository.save(actor);
//            return new ResponseEntity<>(new Message("Successfully added"), HttpStatus.CREATED);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(new Message("Add failed"), HttpStatus.BAD_REQUEST);
//    }
//
//    public ResponseEntity<Message> updateWithCountry(ActorWithCountry actorWithCountry) {
//        try {
//            Actor actor = ActorWithCountry.mapToActor(actorWithCountry);
//
//            boolean isExist = actorRepository.existsById(actor.getId());
//
//            if(isExist) {
//                if (actorWithCountry.getCountryAttr().equals("countryId")) {
//                    Country country = countryRepository.findById(Integer.valueOf(actorWithCountry.getCountryAttrValue())).get();
////                Country country = new Country();
////                country.setId(Integer.valueOf(actorWithCountry.getCountryAttrValue()));
//                    actor.setCountry(country);
//                } else if(actorWithCountry.getCountryAttr().equals("countryName")) {
//                    Country country = countryRepository.getCountryByName(actorWithCountry.getCountryAttrValue()).get();
//                    actor.setCountry(country);
//                } else if (actorWithCountry.getCountryAttr().equals("countryIsoCode")) {
//                    Country country = countryRepository.getCountryByIsoCode(actorWithCountry.getCountryAttrValue()).get();
//                    actor.setCountry(country);
//                }
//                actorRepository.save(actor);
//                return new ResponseEntity<>(new Message("Successfully updated"), HttpStatus.OK);
//            }
//            else {
//                return new ResponseEntity<>(new Message("Actor with given id doesn't exist"), HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(new Message("Update failed"), HttpStatus.BAD_REQUEST);
//    }
}
