package cats2.service;

import cats2.model.dto.CatDto;
import cats2.model.entity.*;
import cats2.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CatService {

    private final CatRepository catRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public CatService(CatRepository catRepo, ModelMapper modelMapper) {
        this.catRepo = catRepo;
        this.modelMapper = modelMapper;
    }

    public List<Cats> getAllCats() {
        return catRepo.findAll();
    }

    public void createCat(CatDto cat) {
        Cats cats = modelMapper.map(cat, Cats.class);
        catRepo.save(cats);
    }

    public Cats getCatById(Integer id) {
        return catRepo.findById(id).get();
    }

    public void deleteCat(Integer id) {
        catRepo.deleteById(id);
    }

    public List<Cats> findOwnerCats(Integer id, List<Cats> cats) {
        List<Cats> catsFound = new ArrayList();

        for (Cats cat: cats) {
            if (cat.getOwnerId() == id) {
                catsFound.add(cat);
            }
        }

        return catsFound;
    }

    public List<Cats> findByColor(Color color) {
        List<Cats> cats = catRepo.findAll();
        List<Cats> foundCats = new ArrayList<>();

        for(Cats cat : cats) {
            if(cat.getCatColor() == color) {
                foundCats.add(cat);
            }
        }

        return foundCats;
    }

    public List<Cats> findByBreed(Breed breed) {
        List<Cats> cats = catRepo.findAll();
        List<Cats> foundCats = new ArrayList<>();

        for(Cats cat : cats) {
            if(cat.getCatBreed() == breed) {
                foundCats.add(cat);
            }
        }

        return foundCats;
    }
}
