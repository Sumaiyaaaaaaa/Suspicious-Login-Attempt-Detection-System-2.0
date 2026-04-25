package dao;

import db.DBConnection;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class LogDAO {

    // Insert login log
    public void insertLog(String username, String status, boolean isSuspicious) {
        try (Connection conn = DBConnection.getConnection()) {

            String query = "INSERT INTO login_logs(username, time, status, is_suspicious) VALUES (?, NOW(), ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, status);
            ps.setBoolean(3, isSuspicious);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get all logs
    public List<Object[]> getAllLogs() {
        List<Object[]> logs = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            String query = "SELECT username, time, status, is_suspicious FROM login_logs";
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                logs.add(new Object[]{
                        rs.getString("username"),
                        rs.getTimestamp("time"),
                        rs.getString("status"),
                        rs.getBoolean("is_suspicious")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return logs;
    }

    // Get only suspicious logs
    public List<Object[]> getSuspiciousLogs() {
        List<Object[]> logs = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            String query = "SELECT username, time, status, is_suspicious FROM login_logs WHERE is_suspicious = true";
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                logs.add(new Object[]{
                        rs.getString("username"),
                        rs.getTimestamp("time"),
                        rs.getString("status"),
                        rs.getBoolean("is_suspicious")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return logs;
    }
}