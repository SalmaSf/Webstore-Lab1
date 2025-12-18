package org.example.webstore.bo;

import java.sql.SQLException;

public class UserHandler {

    public User authenticateUser(String username, String password) throws SQLException {
        User user = User.getUserByUsername(username);
        if (user != null && user.authenticate(password)) {
            return user;
        }
        return null;
    }

    public void registerUser(String username, String password) throws SQLException {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new SQLException("Username or password missing.");
        }

        if (User.userExists(username)) {
            throw new SQLException("Username already exists.");
        }

        User newUser = new User(username, password, "customer");
        newUser.save();
    }

    public User getUserByUsername(String username) throws SQLException {
        return User.getUserByUsername(username);
    }
}
