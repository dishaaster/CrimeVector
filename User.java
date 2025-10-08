package com.crimeapp;
//Abstract base class for login system
public abstract class User {
 protected String username;
 protected String password;

 // Constructor
 public User(String username, String password) {
     this.username = username;
     this.password = password;
 }

 // Abstract method for login validation
 public abstract boolean login(String user, String pass);
}
