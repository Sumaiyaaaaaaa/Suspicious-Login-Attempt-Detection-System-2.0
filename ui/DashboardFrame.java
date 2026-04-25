package ui;

import dao.LogDAO;
import dao.UserDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DashboardFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private JButton refreshButton;
    private JButton suspiciousButton;
    private JButton unlockButton;

    private LogDAO logDAO = new LogDAO();
    private UserDAO userDAO = new UserDAO();

    public DashboardFrame() {
        setTitle("Admin Dashboard");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
        loadAllLogs();

        setVisible(true);
    }

    private void initUI() {
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Username");
        model.addColumn("Time");
        model.addColumn("Status");
        model.addColumn("Suspicious");

        JScrollPane scrollPane = new JScrollPane(table);

        // Buttons
        refreshButton = new JButton("Refresh");
        suspiciousButton = new JButton("Show Suspicious");
        unlockButton = new JButton("Unlock User");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        buttonPanel.add(suspiciousButton);
        buttonPanel.add(unlockButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Actions
        refreshButton.addActionListener(e -> loadAllLogs());

        suspiciousButton.addActionListener(e -> loadSuspiciousLogs());

        unlockButton.addActionListener(e -> unlockSelectedUser());
    }

    private void loadAllLogs() {
        model.setRowCount(0);
        List<Object[]> logs = logDAO.getAllLogs();

        for (Object[] row : logs) {
            model.addRow(row);
        }
    }

    private void loadSuspiciousLogs() {
        model.setRowCount(0);
        List<Object[]> logs = logDAO.getSuspiciousLogs();

        for (Object[] row : logs) {
            model.addRow(row);
        }
    }

    private void unlockSelectedUser() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a user first!");
            return;
        }

        String username = model.getValueAt(selectedRow, 0).toString();

        userDAO.unlockUser(username);

        JOptionPane.showMessageDialog(this, "User unlocked: " + username);
    }
}