package org.example.webstore.ui.dto;

public class UserInfoDTO {
    private final int id;
    private final String username;
    private final String role;

    public UserInfoDTO(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getRole() { return role; }

    public boolean isAdmin() {
        return role != null && "admin".equalsIgnoreCase(role);
    }

    public boolean hasRole(String roleName) {
        return role != null && roleName != null && role.equalsIgnoreCase(roleName);
    }
}
