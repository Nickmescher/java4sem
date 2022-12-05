package kotiki.controllers;

import kotiki.model.*;
import kotiki.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/kotikiUser")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
public class KotikiUserController {

    private final KotikiService kotikiService;

    private final FriendsService friendsService;

    private final OwnerService ownerService;

    @Autowired
    public KotikiUserController(KotikiService kotikiService, FriendsService friendsService, OwnerService ownerService) {
        this.kotikiService = kotikiService;
        this.friendsService = friendsService;
        this.ownerService = ownerService;
    }

    @GetMapping
    public String getAll(
            Map<String, Object> model,
            @AuthenticationPrincipal User user

    ) {
        List<Kotiki> allKotiki = kotikiService.getAllKotiki();
        Integer userId = ownerService.findOwnerByUserId(user.getId());
        List<Kotiki> kotiks = kotikiService.findOwnerKotiki(userId, allKotiki);
        List<Color> colors = List.of(Color.values());
        List<Breed> breeds = List.of(Breed.values());
        model.put("colors", colors);
        model.put("breeds", breeds);
        model.put("kotiki", kotiks);
        return "main/kotikiUser";
    }

    @PostMapping(path = "/create")
    public String createKotik(@AuthenticationPrincipal User user,
                              @RequestParam String name,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
                              @RequestParam Breed breed,
                              @RequestParam Color color) {

        Integer ownerId = ownerService.findOwnerByUserId(user.getId());

        kotikiService.createKotik(name, birthday, breed, color, ownerId);
        return "redirect:/kotikiUser";
    }

    @PostMapping(path = "/update")
    public String updateKotik(@AuthenticationPrincipal User user,
                              @RequestParam Integer id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
                              @RequestParam(required = false) Breed breed,
                              @RequestParam(required = false) Color color) {

        Kotiki kotik = kotikiService.getKotikById(id);
        Integer ownerId = null;
        Integer userId = ownerService.findOwnerByUserId(user.getId());

        if (kotik.getOwnerId() == userId) {
            kotikiService.updateKotik(id, name, birthday, breed, color, ownerId);
        }

        return "redirect:/kotikiUser";
    }

    @PostMapping(path = "/delete")
    public String delete(@AuthenticationPrincipal User user,
                         @RequestParam(required = false) Integer id) {

        Kotiki kotik = kotikiService.getKotikById(id);
        Integer userId = ownerService.findOwnerByUserId(user.getId());

        if (kotik.getOwnerId() == userId) {
            kotikiService.deleteKotik(id);
        }

        return "redirect:/kotikiUser";
    }

    @PostMapping(path = "/getById")
    public String getKotikById(Map<String, Object> model,
                               @AuthenticationPrincipal User user,
                               @RequestParam Integer id) {

        List<Kotiki> allKotiki = kotikiService.getKotikiById(id);
        Integer userId = ownerService.findOwnerByUserId(user.getId());
        List<Kotiki> kotiki = kotikiService.findOwnerKotiki(userId, allKotiki);
        List<Color> colors = List.of(Color.values());
        List<Breed> breeds = List.of(Breed.values());
        model.put("colors", colors);
        model.put("breeds", breeds);
        model.put("kotiki", kotiki);
        return "main/kotikiUser";
    }

    @PostMapping(path = "/getByColor")
    public String getKotikByColor(Map<String, Object> model,
                                  @AuthenticationPrincipal User user,
                                  @RequestParam Color color) {

        List<Kotiki> allKotiki = kotikiService.findByColor(color);
        Integer userId = ownerService.findOwnerByUserId(user.getId());
        List<Kotiki> kotiki = kotikiService.findOwnerKotiki(userId, allKotiki);
        List<Color> colors = List.of(Color.values());
        List<Breed> breeds = List.of(Breed.values());
        model.put("colors", colors);
        model.put("breeds", breeds);
        model.put("kotiki", kotiki);
        return "main/kotikiUser";
    }

    @PostMapping(path = "/getByBreed")
    public String getKotikByBreed(Map<String, Object> model,
                                  @AuthenticationPrincipal User user,
                                  @RequestParam Breed breed) {

        List<Kotiki> allKotiki = kotikiService.findByBreed(breed);
        Integer userId = ownerService.findOwnerByUserId(user.getId());
        List<Kotiki> kotiki = kotikiService.findOwnerKotiki(userId, allKotiki);
        List<Color> colors = List.of(Color.values());
        List<Breed> breeds = List.of(Breed.values());
        model.put("colors", colors);
        model.put("breeds", breeds);
        model.put("kotiki", kotiki);
        return "main/kotikiUser";
    }

    @PostMapping(path = "/getFriends")
    public String getKotikFriends(Map<String, Object> model,
                                  @AuthenticationPrincipal User user,
                                  @RequestParam Integer id) {

        List<Friends> friends = friendsService.getAllFriends();
        List<Integer> kotikiId = friendsService.findKotikFriends(id, friends);
        List<Kotiki> allKotiki = new ArrayList<>();

        for (Integer ids : kotikiId) {
            allKotiki.add(kotikiService.getKotikById(ids));
        }

        Integer userId = ownerService.findOwnerByUserId(user.getId());
        List<Kotiki> kotiki = kotikiService.findOwnerKotiki(userId, allKotiki);
        List<Color> colors = List.of(Color.values());
        List<Breed> breeds = List.of(Breed.values());
        model.put("colors", colors);
        model.put("breeds", breeds);
        model.put("kotiki", kotiki);
        return "main/kotikiUser";
    }
}
