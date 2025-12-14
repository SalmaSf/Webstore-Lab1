package org.example.webstore.bo;

import org.example.webstore.db.UserDB;

public class UserHandler {

    private final UserDB userDB = new UserDB();

    public User authenticate(String username, String password) {
        User user = userDB.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public boolean registerUser(String username, String password) {
        if (userDB.getUserByUsername(username) != null) {
            return false; // Username already exists
        }
        return userDB.addUser(username, password);
    }

}
