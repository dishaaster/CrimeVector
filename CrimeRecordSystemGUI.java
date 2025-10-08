package com.crimeapp;
import javax.swing.*;
import java.awt.*;
import java.util.List;

// Main GUI class
public class CrimeRecordSystemGUI extends JFrame {
    private Admin admin;

    public CrimeRecordSystemGUI() {
        admin = new Admin("admin", "1234"); // Default credentials
        createLoginPage();
    }

    // ---------------- LOGIN PAGE ----------------
    private void createLoginPage() {
        JFrame loginFrame = new JFrame("Crime Record System - Login");
        loginFrame.setSize(400, 250);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(50, 50, 100, 25);
        JTextField txtUser = new JTextField();
        txtUser.setBounds(150, 50, 150, 25);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(50, 100, 100, 25);
        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(150, 100, 150, 25);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 150, 100, 30);

        btnLogin.addActionListener(e -> {
            String user = txtUser.getText();
            String pass = new String(txtPass.getPassword());

            if (admin.login(user, pass)) {
                loginFrame.dispose();
                createMainMenu();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid Credentials!");
            }
        });

        loginFrame.add(lblUser);
        loginFrame.add(txtUser);
        loginFrame.add(lblPass);
        loginFrame.add(txtPass);
        loginFrame.add(btnLogin);
        loginFrame.setVisible(true);
    }

    // ---------------- MAIN MENU ----------------
    private void createMainMenu() {
        JFrame menuFrame = new JFrame("Crime Record Management");
        menuFrame.setSize(500, 400);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnAdd = new JButton("Add Crime Record");
        JButton btnView = new JButton("View All Records");
        JButton btnPredict = new JButton("Predict Crime Trends");
        JButton btnExit = new JButton("Exit");

        btnAdd.addActionListener(e -> addCrimeRecord());
        btnView.addActionListener(e -> viewRecords());
        btnPredict.addActionListener(e -> showPrediction());
        btnExit.addActionListener(e -> System.exit(0));

        menuFrame.add(btnAdd);
        menuFrame.add(btnView);
        menuFrame.add(btnPredict);
        menuFrame.add(btnExit);
        menuFrame.setVisible(true);
    }

    // ---------------- ADD CRIME ----------------
    private void addCrimeRecord() {
        JTextField idField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField locField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField suspectField = new JTextField();

        Object[] inputFields = {
            "Crime ID:", idField,
            "Crime Type:", typeField,
            "Location:", locField,
            "Date:", dateField,
            "Suspect:", suspectField
        };

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Add Crime Record", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            CrimeRecord record = new CrimeRecord(
                idField.getText(),
                typeField.getText(),
                locField.getText(),
                dateField.getText(),
                suspectField.getText()
            );
            admin.addCrimeRecord(record);
        }
    }

    // ---------------- VIEW RECORDS ----------------
    private void viewRecords() {
        List<CrimeRecord> list = admin.getRecords();
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No records found.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (CrimeRecord c : list) {
            sb.append(c.toString()).append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Crime Records", JOptionPane.INFORMATION_MESSAGE);
    }

    // ---------------- PREDICTION ----------------
    private void showPrediction() {
        List<CrimeRecord> list = admin.getRecords();
        String type = CrimeAnalyzer.predictCrimeType(list);
        String area = CrimeAnalyzer.predictHotspot(list);

        JOptionPane.showMessageDialog(null,
            "🔍 Most frequent crime type: " + type + "\n📍 Most crime-prone area: " + area,
            "Predictive Analysis",
            JOptionPane.INFORMATION_MESSAGE);
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {
        new CrimeRecordSystemGUI();
    }
}
