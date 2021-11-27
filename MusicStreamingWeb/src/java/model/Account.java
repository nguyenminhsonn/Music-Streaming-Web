package model;

/**
 *
 * @author admin
 */
public class Account {
    private String username, password, role;
    private boolean active;

    public Account() {
    }

    public Account(String username, String password, String role, boolean active) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Account{" + "username=" + username + ", password=" + password + ", role=" + role + ", active=" + active + '}';
    }
    
    
}
