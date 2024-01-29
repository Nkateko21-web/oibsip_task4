package org.example;

import java.util.*;
public class User {

    private String username;
    private String password;
    private String fullName;
    private String email;

    // Constructor

    public User(String username, String password, String fullName, String email) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }

    // Getter methods
    public String getUsername() {
        return  username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }

    // Setter methods
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
