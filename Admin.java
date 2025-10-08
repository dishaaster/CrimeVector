package com.crimeapp;
import java.util.*;

// Admin class extending User
public class Admin extends User {
    private List<CrimeRecord> records;

    // Constructor
    public Admin(String username, String password) {
        super(username, password);
        records = FileHandler.loadRecords(); // Load data if available
    }

    // Login verification
    @Override
    public boolean login(String user, String pass) {
        return this.username.equals(user) && this.password.equals(pass);
    }

    // Add new crime record
    public void addCrimeRecord(CrimeRecord record) {
        records.add(record);
        System.out.println("✅ Crime record added successfully!");
        FileHandler.saveRecords(records);
    }

    // View all crime records
    public void viewCrimeRecords() {
        if (records.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        for (CrimeRecord c : records) {
            System.out.println(c);
        }
    }

    // Return list (for GUI use)
    public List<CrimeRecord> getRecords() {
        return records;
    }
}
