package model;

import java.sql.Timestamp;

public class Log {
    private String username;
    private Timestamp time;
    private String status;
    private boolean isSuspicious;

    public Log(String username, Timestamp time, String status, boolean isSuspicious) {
        this.username = username;
        this.time = time;
        this.status = status;
        this.isSuspicious = isSuspicious;
    }

    public String getUsername() { return username; }
    public Timestamp getTime() { return time; }
    public String getStatus() { return status; }
    public boolean isSuspicious() { return isSuspicious; }
}