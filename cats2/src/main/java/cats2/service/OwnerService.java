package cats2.service;

import cats2.model.entity.*;
import cats2.repository.*;
import cats2.model.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public OwnerService(OwnerRepository ownerRepo, ModelMapper modelMapper) {
        this.ownerRepo = ownerRepo;
        this.modelMapper = modelMapper;
    }

    public List<Owner> getAllOwners() {
        return ownerRepo.findAll();
    }

    public void createOwner(OwnerDto user) {
        Owner owner = modelMapper.map(user, Owner.class);
        ownerRepo.save(owner);
    }

    public Owner getOwnerById(Integer id) {
        return ownerRepo.findById(id).get();
    }

    public void deleteOwner(Integer id) {
        ownerRepo.deleteById(id);
    }
}
