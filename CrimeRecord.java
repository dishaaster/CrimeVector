package com.crimeapp;

public class CrimeRecord {
    private String criminalName;
    private String crimeType;
    private String location;
    private String date;
    private String status;   // NEW FIELD

    public CrimeRecord(String criminalName, String crimeType,
                       String location, String date, String status) {
        this.criminalName = criminalName;
        this.crimeType = crimeType;
        this.location = location;
        this.date = date;
        this.status = status;
    }

    // Getters
    public String getCriminalName() { return criminalName; }
    public String getCrimeType()    { return crimeType; }
    public String getLocation()     { return location; }
    public String getDate()         { return date; }
    public String getStatus()       { return status; }

    // Setters
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return criminalName + "," + crimeType + "," + location + "," + date + "," + status;
    }
}
