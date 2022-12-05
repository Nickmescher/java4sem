package kotiki.controllers;

import kotiki.model.Kotiki;
import kotiki.model.Owner;
import kotiki.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/owners")
@PreAuthorize("hasAuthority('ADMIN')")
public class OwnerController {

    private final OwnerService ownerService;

    private final KotikiService kotikiService;

    @Autowired
    public OwnerController(OwnerService ownerService, KotikiService kotikiService) {
        this.ownerService = ownerService;
        this.kotikiService = kotikiService;
    }


    @GetMapping
    public String findAllOwners(Map<String, Object> model) {
        List<Owner> owners = ownerService.getAllOwners();
        model.put("owners", owners);
        return "main/owners";
    }

    @PostMapping(path = "/create")
    public String createOwner(@RequestParam String name,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
                              @RequestParam Integer userId) {

        ownerService.createOwner(name, birthday, userId);
        return "redirect:/owners";
    }

    @PostMapping(path = "/update")
    public String updateOwner(@RequestParam Integer id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
                              @RequestParam Integer userId) {

        ownerService.updateOwner(id, name, birthday, userId);
        return "redirect:/owners";
    }

    @PostMapping(path = "/delete")
    public String delete(@RequestParam(required = false) Integer id) {
        ownerService.deleteOwner(id);
        return "redirect:/owners";
    }

    @PostMapping(path = "/getById")
    public String getOwnerById(Map<String, Object> model,
                               @RequestParam Integer id) {
        List<Owner> owners = ownerService.getOwnersById(id);
        model.put("owners", owners);
        return "main/owners";
    }

    @PostMapping(path = "/getKotiki")
    public String getOwnerKotiki(Map<String, Object> model,
                                 @RequestParam Integer id) {
        List<Kotiki> allkotiki = kotikiService.getAllKotiki();
        List<Kotiki> kotiki = kotikiService.findOwnerKotiki(id, allkotiki);

        model.put("kotiki", kotiki);
        return "main/kotiki";
    }
}
