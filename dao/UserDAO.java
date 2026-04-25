package dao;

import db.DBConnection;
import java.sql.*;

public class UserDAO {

    // Validate login (only if not locked)
    public boolean validateUser(String username, String password) {
        boolean isValid = false;

        try (Connection conn = DBConnection.getConnection()) {

            String query = "SELECT * FROM users WHERE username=? AND password=? AND is_locked=false";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                isValid = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }

    // Check if user is locked
    public boolean isUserLocked(String username) {
        boolean locked = false;

        try (Connection conn = DBConnection.getConnection()) {

            String query = "SELECT is_locked FROM users WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                locked = rs.getBoolean("is_locked");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return locked;
    }

    // Lock user
    public void lockUser(String username) {
        try (Connection conn = DBConnection.getConnection()) {

            String query = "UPDATE users SET is_locked = true WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, username);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Unlock user
    public void unlockUser(String username) {
        try (Connection conn = DBConnection.getConnection()) {

            String query = "UPDATE users SET is_locked = false WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, username);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}