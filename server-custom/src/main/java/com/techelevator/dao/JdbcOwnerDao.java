package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Owner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcOwnerDao implements OwnerDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcOwnerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Owner createOwner(Owner owner) {
        String sql = "INSERT INTO owners (user_id, first_name, last_name, address, phone, volunteer) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, owner.getOwnerId(), owner.getFirstName(), owner.getLastName(), owner.getAddress(), owner.getPhone(), owner.isVolunteer());
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Error creating owner", e);
        }
        return owner;
    }
    @Override
    public Owner getOwnerById(int ownerId) {
        String sql = "SELECT * FROM owners WHERE owner_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, ownerId);
            if (results.next()) {
                return mapRowToOwner(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error retrieving owner", e);
        }
        return null;
    }
    @Override
    public List<Owner> getAllOwners() {
        String sql = "SELECT * FROM owners";
        List<Owner> owners = new ArrayList<>();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                owners.add(mapRowToOwner(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error retrieving owners", e);
        }
        return owners;
    }
    @Override
    public Owner updateOwner(Owner owner) {
        String sql = "UPDATE owners SET first_name = ?, last_name = ?, address = ?, phone = ?, volunteer = ? WHERE owner_id = ?";
        try {
            jdbcTemplate.update(sql, owner.getFirstName(), owner.getLastName(), owner.getAddress(), owner.getOwnerId(), owner.getPhone(), owner.isVolunteer());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error updating owner", e);
        }
        return owner;
    }
    @Override
    public Owner deleteOwner(int ownerId) {
        String sql = "DELETE FROM owners WHERE owner_id = ?";
        try {
            jdbcTemplate.update(sql, ownerId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error deleting owner", e);
        }
        return null;
    }

    private Owner mapRowToOwner(SqlRowSet rs) {
        Owner owner = new Owner();
        owner.setOwnerId(rs.getInt("owner_id"));
        owner.setFirstName(rs.getString("first_name"));
        owner.setLastName(rs.getString("last_name"));
        owner.setAddress(rs.getString("address"));
        owner.setPhone(rs.getString("phone"));
        owner.setVolunteer(rs.getBoolean("volunteer"));
        return owner;
    }
}