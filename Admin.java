package com.crimeapp;

import java.util.*;

public class Admin {
    private FileHandler fileHandler;
    private List<CrimeRecord> records;
    private Scanner sc;

    public Admin(Scanner sc) {
        this.sc = sc;
        this.fileHandler = new FileHandler();
        this.records = fileHandler.loadRecords();
    }

    public void addCrimeRecord() {
        System.out.print("Enter Criminal Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Crime Type: ");
        String type = sc.nextLine();
        System.out.print("Enter Location: ");
        String location = sc.nextLine();
        System.out.print("Enter Date (DD-MM-YYYY): ");
        String date = sc.nextLine();
        System.out.print("Enter Status (Active/Resolved): ");
        String status = sc.nextLine();

        CrimeRecord record = new CrimeRecord(name, type, location, date, status);
        records.add(record);
        fileHandler.saveRecord(record);
        System.out.println("✅ Record added successfully!");
    }

    public void viewAllRecords() {
        if (records.isEmpty()) System.out.println("No records found.");
        else {
            System.out.println("\n----- All Crime Records -----");
            for (CrimeRecord r : records) {
                System.out.println(r.getCriminalName() + " | " + r.getCrimeType() + " | " +
                        r.getLocation() + " | " + r.getDate() + " | " + r.getStatus());
            }
        }
    }

    public void searchRecord() {
        System.out.print("Enter name to search: ");
        String searchName = sc.nextLine();
        boolean found = false;
        for (CrimeRecord r : records) {
            if (r.getCriminalName().equalsIgnoreCase(searchName)) {
                System.out.println("Record Found: " + r);
                found = true;
            }
        }
        if (!found) System.out.println("❌ No record found with that name.");
    }

    public void deleteRecord() {
        System.out.print("Enter name to delete: ");
        String name = sc.nextLine();
        boolean removed = records.removeIf(r -> r.getCriminalName().equalsIgnoreCase(name));
        if (removed) {
            fileHandler.saveAll(records);
            System.out.println("✅ Record deleted!");
        } else System.out.println("❌ Record not found.");
    }

    public void markResolved() {
        System.out.print("Enter name to mark resolved: ");
        String name = sc.nextLine();
        boolean found = false;
        for (CrimeRecord r : records) {
            if (r.getCriminalName().equalsIgnoreCase(name)) {
                r.setStatus("Resolved");
                found = true;
                break;
            }
        }
        if (found) {
            fileHandler.saveAll(records);
            System.out.println("✅ Case marked resolved!");
        } else System.out.println("❌ Record not found.");
    }

    public void viewActiveCases() {
        System.out.println("\n----- Active Cases -----");
        boolean any = false;
        for (CrimeRecord r : records) {
            if (r.getStatus().equalsIgnoreCase("Active")) {
                System.out.println(r.getCriminalName() + " | " + r.getCrimeType() + " | " +
                        r.getLocation() + " | " + r.getDate() + " | " + r.getStatus());
                any = true;
            }
        }
        if (!any) System.out.println("No active cases.");
    }

    public void showAdminMenu() {
        int choice;
        do {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Add Crime Record");
            System.out.println("2. View All Records");
            System.out.println("3. Search Record");
            System.out.println("4. Delete Record");
            System.out.println("5. Mark Case as Resolved");
            System.out.println("6. View Active Cases");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Enter a valid number:");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addCrimeRecord();
                case 2 -> viewAllRecords();
                case 3 -> searchRecord();
                case 4 -> deleteRecord();
                case 5 -> markResolved();
                case 6 -> viewActiveCases();
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }
}
