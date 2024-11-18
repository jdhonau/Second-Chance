package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Pet;
import com.techelevator.model.User;
import com.techelevator.model.Volunteer;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcVolunteerDao implements VolunteerDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcVolunteerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Volunteer createVolunteer(Volunteer volunteer) {

        // Generate a unique username
        String username = generateUniqueUsername("VolGenUser");
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setHashedPassword("$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem");
        newUser.setRole("USER");

        // Insert into users table and get the generated user_id
        String userSql = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?) RETURNING user_id";
        KeyHolder userKeyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, newUser.getUsername());
                ps.setString(2, newUser.getHashedPassword());
                ps.setString(3, newUser.getRole());
                return ps;
            }, userKeyHolder);
            int userId = userKeyHolder.getKey().intValue();
            volunteer.setUser_id(userId);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Error creating user for volunteer", e);
        }

        // Insert into Volunteer table with the generated user_id
        String volunteerSql = "INSERT INTO Volunteer (user_id, app_status) VALUES (?, ?) RETURNING volnumber";
        KeyHolder volunteerKeyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(volunteerSql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, volunteer.getUser_id());
                ps.setString(2, volunteer.getApp_status());
                return ps;
            }, volunteerKeyHolder);
            int volNumber = volunteerKeyHolder.getKey().intValue();
            volunteer.setVolnumber(volNumber);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Error creating volunteer", e);
        }
        return volunteer;
    }

    private String generateUniqueUsername(String baseUsername) {
        String username = baseUsername;
        int counter = 1;
        while (true) {
            String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
            int count = jdbcTemplate.queryForObject(sql, Integer.class, username);
            if (count == 0) {
                break;
            }
            username = baseUsername + counter;
            counter++;
        }
        return username;
    }

    @Override
    public Volunteer getVolunteerById(int volnumber) {
        String sql = "SELECT * FROM Volunteer WHERE VolNumber = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, volnumber);
            if (results.next()) {
                return mapRowToVolunteer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error retrieving volunteer", e);
        }
        return null;
    }
    @Override
    public List<Volunteer> getAllVolunteers() {
        String sql = "SELECT * FROM Volunteer";
        List<Volunteer> volunteers = new ArrayList<>();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                volunteers.add(mapRowToVolunteer(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error retrieving volunteers", e);
        }
        return volunteers;


    }

        @Override
    public void approveVolunteer(int volnumber) {
        String sql = "UPDATE Volunteer SET app_status = 'Approved' WHERE VolNumber = ?";
        try {
            jdbcTemplate.update(sql, volnumber);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error approving volunteer", e);
        }
    }

    @Override
    public Volunteer updateVolunteer(int volnumber, Volunteer volunteer) {
        String sql = "UPDATE Volunteer SET user_id = ?, app_status = ? WHERE VolNumber = ?";
        try {
            jdbcTemplate.update(sql, volunteer.getUser_id(), volunteer.getApp_status(), volnumber);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error updating volunteer", e);
        }

        return volunteer;
    }
    @Override
    public boolean deleteVolunteer(int volnumber) {
        String sql = "DELETE FROM Volunteer WHERE VolNumber = ?";
        try {
            jdbcTemplate.update(sql, volnumber);
            return true;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error deleting volunteer", e);
        }
    }

    private Volunteer mapRowToVolunteer(SqlRowSet rs) {
        Volunteer volunteer = new Volunteer();
        volunteer.setVolnumber(rs.getInt("VolNumber"));
        volunteer.setUserId(rs.getInt("user_id"));
        volunteer.setAppStatus(rs.getString("app_status"));
        return volunteer;
    }
}



//Approve Volunteers
//As an admin, I want to see all pending volunteer applications and have the option to approve or decline them.
//Approving an application should create a new volunteer user (who will then be able to login).
//Declining an application should remove the application from list of pending applications, but not remove the data from the database.



//Access Volunteer directory
//As a volunteer, I want a listing of other volunteers and admins with contact info.