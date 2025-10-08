package com.crimeapp;
import java.io.Serializable;

// Stores details of one crime record
public class CrimeRecord implements Serializable {
    private String crimeID;
    private String crimeType;
    private String location;
    private String date;
    private String suspect;

    // Constructor
    public CrimeRecord(String crimeID, String crimeType, String location, String date, String suspect) {
        this.crimeID = crimeID;
        this.crimeType = crimeType;
        this.location = location;
        this.date = date;
        this.suspect = suspect;
    }

    // Getters
    public String getCrimeID() { return crimeID; }
    public String getCrimeType() { return crimeType; }
    public String getLocation() { return location; }
    public String getDate() { return date; }
    public String getSuspect() { return suspect; }

    // Display format
    @Override
    public String toString() {
        return "CrimeID: " + crimeID +
               " | Type: " + crimeType +
               " | Location: " + location +
               " | Date: " + date +
               " | Suspect: " + suspect;
    }
}
