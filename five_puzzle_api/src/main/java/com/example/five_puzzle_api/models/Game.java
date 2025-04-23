package com.example.five_puzzle_api.models;

public class Game {
    private final String id; // string as the id should be able to be unique, for now i have it as a counter
    private final String userId; // to see what user is associated with the game
    private final String gameState; // sat to string to begin with, will be changed to some 2d array later on

    public Game(String id, String userId, String gameState) {
        this.id = id;
        this.userId = userId;
        this.gameState = gameState;
    }
    public String getId() {
        return id;
    }
    public String getUserId() {
        return userId;
    }
    public String getGameState() {
        return gameState;
    }
    
}
