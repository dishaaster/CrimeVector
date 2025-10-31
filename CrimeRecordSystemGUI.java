package com.crimeapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class CrimeRecordSystemGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;

    public CrimeRecordSystemGUI() {
        // Window setup
        setTitle("Crime Record System - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        messageLabel = new JLabel("", SwingConstants.CENTER);

        add(userLabel);
        add(usernameField);
        add(passLabel);
        add(passwordField);
        add(new JLabel());
        add(loginButton);
        add(new JLabel());
        add(messageLabel);

        loginButton.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Hardcoded credentials
        if (username.equalsIgnoreCase("admin") && password.equals("12345")) {
            messageLabel.setText("✅ Login successful!");
            JOptionPane.showMessageDialog(this, "Welcome, Admin!");
            dispose();
            new CrimeDashboardGUI().setVisible(true);
        } else {
            messageLabel.setText("❌ Invalid credentials. Try again.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CrimeRecordSystemGUI gui = new CrimeRecordSystemGUI();
            gui.setVisible(true);
        });
    }
}
