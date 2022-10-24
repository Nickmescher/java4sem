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
@RequestMapping("/friends")
public class FriendsController {

    private final FriendsService friendsService;

    @Autowired
    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @GetMapping(path="/all")
    public List<Friends> findAllFriends() {
        return friendsService.getAllFriends();
    }

    @GetMapping(path="/get/{id}")
    public ResponseEntity<Friends> getKotikById(@PathVariable Integer id) {
        try {
            Friends friends = friendsService.getFriendsById(id);
            return new ResponseEntity<>(friends, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path="/create")
    public ResponseEntity<?> createFriends(@RequestBody FriendsDto friends) {
        try {
            friendsService.createFriends(friends);
            return new ResponseEntity<>(friends, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<?> updateFriends(@RequestBody FriendsDto friends, @PathVariable Integer id) {
        try {

            Friends existFriends = friendsService.getFriendsById(id);
            if (existFriends == null){
                return new ResponseEntity<>(MessageFormat
                        .format("Friend with id=", id, " not found"), HttpStatus.NOT_FOUND);
            }
            friends.setId(id);
            friendsService.createFriends(friends);
            return new ResponseEntity<>(friends, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity deleteFriends(@PathVariable Integer id) {
        try {
            friendsService.deleteFriends(id);
            return ResponseEntity.ok().body("Deleted friends");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
