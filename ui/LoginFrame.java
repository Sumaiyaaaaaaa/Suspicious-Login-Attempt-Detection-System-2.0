package ui;

import service.AuthService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private AuthService authService = new AuthService();

    public LoginFrame() {
        setTitle("Login System");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel());
        loginButton = new JButton("Login");
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        String result = authService.login(username, password);

        if (result.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            new DashboardFrame();
            dispose();

        } else if (result.equals("LOCKED")) {
            JOptionPane.showMessageDialog(this, "Account locked due to multiple failed attempts!");

        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials!");
        }
    }
}