package com.techelevator.controller;

import com.techelevator.model.Pet;
import com.techelevator.dao.PetDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetDao petDao;

    public PetController(PetDao petDao) {
        this.petDao = petDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    @PreAuthorize("permitAll()")
    public List<Pet> getAllActivePets() {
        return petDao.getAllActivePets();
    }

    @RequestMapping(value = "/{petId}", method = RequestMethod.GET)
    public Pet getPetById(@PathVariable int petId) {
        Pet pet = petDao.getPetById(petId);
        if (pet != null) {
            return pet;
        } else {
            throw new RuntimeException("Pet not found.");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Pet addPet(@Valid @RequestBody Pet pet) {
        return petDao.createPet(pet);
    }

    @RequestMapping(path = "/{petId}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Pet updatePet(@Valid @RequestBody Pet pet, @PathVariable int petId) {
        pet.setAnimalid(petId);
        return petDao.updatePet(pet);
    }

    @RequestMapping(path = "/{petId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Pet deletePet(@PathVariable int petId) {
        petDao.deletePet(petId);
        return null;
    }

    @RequestMapping(path = "/whoami")
    public String whoAmI() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
