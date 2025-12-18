package org.example.webstore.db;

import org.example.webstore.bo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB {

    private UserDB() { }

    public static User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT id, username, password, role FROM user WHERE username = ?";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("role")
                    );
                }
            }
        }
        return null;
    }

    public static boolean userExists(String username) throws SQLException {
        String sql = "SELECT 1 FROM user WHERE username = ?";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Matches their createUser(User u)
    public static void createUser(User user) throws SQLException {
        String sql = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            // Default to customer if role not set
            String role = (user.getRole() == null || user.getRole().isBlank()) ? "customer" : user.getRole();
            stmt.setString(3, role);

            stmt.executeUpdate();
        }
    }
}
