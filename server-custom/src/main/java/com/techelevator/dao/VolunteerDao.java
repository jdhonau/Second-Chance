package com.techelevator.dao;

import com.techelevator.model.Volunteer;
import java.util.List;

public interface VolunteerDao {

    Volunteer getVolunteerById(int volnumber);

    List<Volunteer> getAllVolunteers();

    Volunteer updateVolunteer(int volnumber, Volunteer volunteer);

    boolean deleteVolunteer(int volnumber);

    Volunteer createVolunteer(Volunteer volunteer);

    void approveVolunteer(int volnumber);

}
