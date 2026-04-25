package service;

import dao.UserDAO;
import dao.LogDAO;

import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private UserDAO userDAO = new UserDAO();
    private LogDAO logDAO = new LogDAO();

    private Map<String, Integer> failedAttemptsMap = new HashMap<>();

    private static final int SUSPICIOUS_THRESHOLD = 3;
    private static final int LOCK_THRESHOLD = 5;

    public String login(String username, String password) {

        // Check if already locked
        if (userDAO.isUserLocked(username)) {
            return "LOCKED";
        }

        boolean success = userDAO.validateUser(username, password);

        if (success) {
            failedAttemptsMap.put(username, 0);
            logDAO.insertLog(username, "SUCCESS", false);
            return "SUCCESS";

        } else {
            int attempts = failedAttemptsMap.getOrDefault(username, 0) + 1;
            failedAttemptsMap.put(username, attempts);

            boolean isSuspicious = attempts >= SUSPICIOUS_THRESHOLD;

            if (attempts >= LOCK_THRESHOLD) {
                userDAO.lockUser(username);
                logDAO.insertLog(username, "FAILED", true);
                return "LOCKED";
            }

            logDAO.insertLog(username, "FAILED", isSuspicious);
            return "FAILED";
        }
    }
}