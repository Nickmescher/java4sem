package kotiki.controllers;

import kotiki.model.Friends;
import kotiki.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/friends")
@PreAuthorize("hasAuthority('ADMIN')")
public class FriendsController {

    private final FriendsService friendsService;

    @Autowired
    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @GetMapping
    public String findAllFriends(Map<String, Object> model) {
        List<Friends> friends = friendsService.getAllFriends();
        model.put("friends", friends);
        return "main/friends";
    }

    @PostMapping(path = "/create")
    public String createFriends(@RequestParam Integer kotik1,
                                @RequestParam Integer kotik2) {

        friendsService.createFriends(kotik1, kotik2);
        return "redirect:/friends";
    }

    @PostMapping(path = "/update")
    public String updateFriend(@RequestParam Integer id,
                               @RequestParam(required = false) Integer kotik1,
                               @RequestParam(required = false) Integer kotik2) {

        friendsService.updateFriend(id, kotik1, kotik2);
        return "redirect:/friends";
    }

    @PostMapping(path = "/delete")
    public String delete(@RequestParam(required = false) Integer id) {
        friendsService.deleteFriends(id);
        return "redirect:/friends";
    }

    @PostMapping(path = "/getById")
    public String getFriendById(Map<String, Object> model,
                                @RequestParam Integer id) {
        List<Friends> friends = friendsService.getFriendsById(id);
        model.put("friends", friends);
        return "main/friends";
    }
}
