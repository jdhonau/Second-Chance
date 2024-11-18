package com.techelevator.dao;

import com.techelevator.model.User;
import java.util.List;

public interface UserDao {
    User getUserById(int userId);

    List<User> getUsers();

    User getUserByUsername(String username);

    User createUser(User newUser);

    User authenticateUser(String username, String password);
}
