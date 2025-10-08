package com.crimeapp;
import java.io.*;
import java.util.*;

// Handles saving and loading data to file
public class FileHandler {

    private static final String FILE_NAME = "crime_records.dat";

    // Save records to file
    public static void saveRecords(List<CrimeRecord> records) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(records);
            System.out.println("✅ Records saved successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error saving records: " + e.getMessage());
        }
    }

    // Load records from file
    @SuppressWarnings("unchecked")
    public static List<CrimeRecord> loadRecords() {
        List<CrimeRecord> records = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            records = (List<CrimeRecord>) ois.readObject();
            System.out.println("✅ Records loaded successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("No previous records found, starting fresh...");
        } catch (Exception e) {
            System.out.println("❌ Error loading records: " + e.getMessage());
        }
        return records;
    }
}
