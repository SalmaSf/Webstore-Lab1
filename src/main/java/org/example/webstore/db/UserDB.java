package org.example.webstore.db;

import org.example.webstore.bo.User;
import java.sql.*;

public class UserDB {

    public User getUserByUsername(String username) {
        String query = "SELECT id, username, password, role FROM user WHERE username = ?";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public boolean addUser(String username, String password) {
        String query = "INSERT INTO user (username, password, role) VALUES (?, ?, 'customer')";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public User getUser(String username, String password) {
        try (Connection conn = DBManager.getConnection()) {

            String sql = "SELECT * FROM user WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        null,
                        rs.getString("role")
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
