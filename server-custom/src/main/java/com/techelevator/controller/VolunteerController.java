package com.techelevator.controller;

import com.techelevator.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import com.techelevator.dao.VolunteerDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation .*;

import java.util.List;
@CrossOrigin
@RestController
@PreAuthorize( "hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@RequestMapping("/api/volunteers")
public class VolunteerController {
    @Autowired
    private VolunteerDao volunteerDao;

    public VolunteerController(VolunteerDao volunteerDao) {
        this.volunteerDao = volunteerDao;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_USER') OR ('ROLE_ADMIN')")
    public List<Volunteer> getAllVolunteers() {
        return volunteerDao.getAllVolunteers();
    }

    @GetMapping("/{volnumber}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable int volnumber) {
        Volunteer volunteer = volunteerDao.getVolunteerById(volnumber);
        if (volunteer != null) {
            return new ResponseEntity<>(volunteer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/apply")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer newVolunteer = volunteerDao.createVolunteer(volunteer);
        return new ResponseEntity<>(newVolunteer, HttpStatus.CREATED);
    }

    @PutMapping("/{volnumber}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable int volnumber, @RequestBody Volunteer volunteer) {
        Volunteer updatedVolunteer = volunteerDao.updateVolunteer(volnumber, volunteer);
        if (updatedVolunteer != null) {
            return new ResponseEntity<>(updatedVolunteer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{volnumber}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable int volnumber) {
        if (volunteerDao.deleteVolunteer(volnumber)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/approve/{volnumber}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> approveVolunteer(@PathVariable int volnumber) {
        volunteerDao.approveVolunteer(volnumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/whoami")
    public String whoAmI() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}