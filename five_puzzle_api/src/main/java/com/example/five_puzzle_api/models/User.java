package com.example.five_puzzle_api.models;

import com.example.five_puzzle_api.types.UserType;

public class User {
    private String username; // string as it is a name
    private String password; // string for now, should be hashed
    private final String id; // string as the id should be able to be unique, for now i have it as a counter
    private UserType userType; // string for now, could make a Role class if needed later on

    public User(String username, String password, String id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = UserType.USER; // default role
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setRole(UserType userType) {
        this.userType = userType;
    }
    
}
