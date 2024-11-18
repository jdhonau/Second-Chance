package com.techelevator.controller;

import com.techelevator.dao.OwnerDao;
import com.techelevator.model.Owner;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerDao ownerDao;

    public OwnerController(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @PreAuthorize("permitAll()")
    public Owner createOwner(@Valid @RequestBody Owner owner) {
        return ownerDao.createOwner(owner);
    }

    @GetMapping("/{id}")
    public Owner getOwnerById(@PathVariable int id) {
        Owner owner = ownerDao.getOwnerById(id);
        if (owner != null) {
            return owner;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerDao.getAllOwners();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable int id, @RequestBody Owner owner) {
        owner.setOwnerId(id);
        ownerDao.updateOwner(owner);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable int id) {
        ownerDao.deleteOwner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/whoami")
    public String whoAmI() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
