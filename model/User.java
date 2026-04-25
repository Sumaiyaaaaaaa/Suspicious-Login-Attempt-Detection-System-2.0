package model;

public class User {
    private String username;
    private String password;
    private boolean isLocked;

    public User() {}

    public User(String username, String password, boolean isLocked) {
        this.username = username;
        this.password = password;
        this.isLocked = isLocked;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isLocked() { return isLocked; }
    public void setLocked(boolean locked) { isLocked = locked; }
}