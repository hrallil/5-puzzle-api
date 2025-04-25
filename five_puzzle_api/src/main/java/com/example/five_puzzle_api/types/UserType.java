package com.example.five_puzzle_api.types;

public enum UserType {
    USER("user"),
    ADMIN("admin");

    private final String userType;

    UserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        switch (userType) {
            case "user" -> {
                return "USER";
            }
            case "admin" -> {
                return "ADMIN";
            }
            default -> throw new IllegalArgumentException("Unknown user type: " + userType);
        }
    }
}