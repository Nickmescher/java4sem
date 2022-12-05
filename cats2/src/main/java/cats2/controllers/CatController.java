package cats2.controllers;

import cats2.model.dto.CatDto;
import cats2.model.entity.*;
import cats2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cats2")
public class CatController {

    private final CatService catService;

    private final FriendsService friendsService;

    @Autowired
    public CatController(CatService catService, FriendsService friendsService) {
        this.catService = catService;
        this.friendsService = friendsService;
    }

    @GetMapping(path="/all")
    public List<Cats> findAllCats() {
        return catService.getAllCats();
    }

    @GetMapping(path="/get/{id}")
    public ResponseEntity<Cats> getCatById(@PathVariable Integer id) {
        try {
            Cats cat = catService.getCatById(id);
            return new ResponseEntity<>(cat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/getByColor/{color}")
    public ResponseEntity<List<Cats>> getCatById(@PathVariable Color color) {
        try {
            List<Cats> cat = catService.findByColor(color);
            return new ResponseEntity<>(cat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/getByBreed/{breed}")
    public ResponseEntity<List<Cats>> getCatById(@PathVariable Breed breed) {
        try {
            List<Cats> cat = catService.findByBreed(breed);
            return new ResponseEntity<>(cat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/getFriends/{id}")
    public ResponseEntity<List<Cats>> getCatFriends(@PathVariable Integer id) {
        try {
            List<Friends> friends = friendsService.getAllFriends();
            List<Integer> catId = friendsService.findCatFriends(id, friends);
            List<Cats> cats = new ArrayList<>();

            for (Integer ids : catId) {
                cats.add(catService.getCatById(ids));
            }

            return ResponseEntity.ok().body(cats);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path="/create")
    public ResponseEntity createCat(@RequestBody CatDto cat) {
        try {
            catService.createCat(cat);
            return new ResponseEntity<>(cat, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<?> updateKotik(@RequestBody CatDto cat, @PathVariable Integer id) {
        try {
            Cats existCat = catService.getCatById(id);
            if (existCat == null){
                return new ResponseEntity<>(MessageFormat
                        .format("Cat with id=", id, " not found"), HttpStatus.NOT_FOUND);
            }
            cat.setId(id);
            catService.createCat(cat);
            return new ResponseEntity<>(cat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity deleteCat(@PathVariable Integer id) {
        try {
            catService.deleteCat(id);
            return ResponseEntity.ok().body("Deleted cat");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
