package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Pet;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcPetDao implements PetDao {

    private final JdbcTemplate jdbcTemplate;
    private List<Pet> pets;

    public JdbcPetDao(JdbcTemplate jdbcTemplate, List<Pet> pets) {
        this.jdbcTemplate = jdbcTemplate;
        this.pets = pets;
    }

    public Pet createPet(Pet pet) {
        String sql = "INSERT INTO pets (PetName, breed, color, age, photo, species, active, owner_id, adoption_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, pet.getPetName(), pet.getBreed(), pet.getColor(), pet.getAge(), pet.getPhoto(), pet.getSpecies(), pet.isActive(), pet.getOwner_id(), pet.getAdoption_date());
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return pet;
    }

    @Override
    public Pet getPetById(int animalid) {
        Pet pet = null;
        String sql = "SELECT * FROM pets WHERE animalid = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, animalid);
            if (results.next()) {
                pet = mapRowToPet(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return pet;
    }

    public List<Pet> getAllActivePets() {
        String sql = "SELECT * FROM pets ORDER BY animalid";
        List<Pet> pets = new ArrayList<>();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                pets.add(mapRowToPet(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return pets;
    }

    @Override
    public Pet updatePet(Pet pet) {
        String sql = "UPDATE pets SET PetName = ?, breed = ?, color = ?, age = ?, photo = ?, species = ?, active = ?, owner_id = ?, adoption_date = ? WHERE animalid = ?";

        try {
            jdbcTemplate.update(sql, pet.getPetName(), pet.getBreed(), pet.getColor(), pet.getAge(), pet.getPhoto(), pet.getSpecies(), pet.isActive(), pet.getOwner_id(), pet.getAdoption_date(), pet.getAnimalid());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return pet;
    }

    @Override
    public Pet deletePet(int animalid) {
        String sql = "DELETE FROM pets WHERE animalid = ?";
        try {
            jdbcTemplate.update(sql, animalid);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return null;
    }

    private Pet mapRowToPet(SqlRowSet rs) {
        Pet pet = new Pet();
        pet.setAnimalid(rs.getInt("animalid"));
        pet.setPetName(rs.getString("PetName"));
        pet.setBreed(rs.getString("breed"));
        pet.setColor(rs.getString("color"));
        pet.setAge(rs.getInt("age"));
        pet.setPhoto(rs.getString("photo"));
        pet.setSpecies(rs.getString("species"));
        pet.setActive(rs.getBoolean("active"));
        pet.setOwner_id(rs.getInt("owner_id"));
        if (rs.getDate("adoption_date") != null) {
            pet.setAdoption_date(rs.getDate("adoption_date").toLocalDate());
        }
        return pet;
    }
}
