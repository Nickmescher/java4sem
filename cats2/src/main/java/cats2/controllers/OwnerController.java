package cats2.controllers;

import cats2.model.dto.*;
import cats2.model.entity.*;
import cats2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    private final CatService catService;

    @Autowired
    public OwnerController(OwnerService ownerService, CatService catService) {
        this.ownerService = ownerService;
        this.catService = catService;
    }


    @GetMapping(path="/all")
    public List<Owner> findAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping(path="/get/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Integer id) {
        try {
            Owner owner = ownerService.getOwnerById(id);
            return new ResponseEntity<>(owner, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/getCats/{id}")
    public ResponseEntity<List<Cats>> getOwnerCats(@PathVariable Integer id) {
        try {
            List<Cats> cats = catService.getAllCats();
            List<Cats> catsFound = catService.findOwnerCats(id, cats);

            return ResponseEntity.ok().body(catsFound);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path="/create")
    public ResponseEntity createOwner(@RequestBody OwnerDto owner) {
        try {
            ownerService.createOwner(owner);
            return new ResponseEntity<>(owner, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<?> updateOwner(@RequestBody OwnerDto owner, @PathVariable Integer id) {
        try {
            Owner existOwner = ownerService.getOwnerById(id);
            if (existOwner == null){
                return new ResponseEntity<>(MessageFormat
                        .format("Owner with id=", id, " not found"), HttpStatus.NOT_FOUND);
            }
            owner.setId(id);
            ownerService.createOwner(owner);
            return new ResponseEntity<>(owner, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity deleteOwner(@PathVariable Integer id) {
        try {
            ownerService.deleteOwner(id);
            return ResponseEntity.ok().body("Deleted owner");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
