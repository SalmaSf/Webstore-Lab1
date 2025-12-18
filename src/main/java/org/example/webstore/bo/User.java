package org.example.webstore.bo;

import org.example.webstore.db.UserDB;

import java.sql.SQLException;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;


    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public User(String username, String password, String role) {
        this(0, username, password, role);
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public boolean isAdmin() {
        return role != null && "admin".equalsIgnoreCase(role);
    }

    public boolean authenticate(String inputPassword) {
        return password != null && password.equals(inputPassword);
    }


    public static User getUserByUsername(String username) throws SQLException {
        return UserDB.getUserByUsername(username);
    }

    public void save() throws SQLException {
        UserDB.createUser(this);
    }


    public static boolean userExists(String username) throws SQLException {
        return UserDB.userExists(username);
    }
}
