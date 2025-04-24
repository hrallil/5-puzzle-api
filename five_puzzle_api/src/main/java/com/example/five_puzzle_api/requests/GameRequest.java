package com.example.five_puzzle_api.requests;

public class GameRequest {
    private final String gameId;
    private final String userId;

    public GameRequest(String gameId, String userId) {
        this.gameId = gameId;
        this.userId = userId;
    }

    public String getGameId() {
        return this.gameId;
    }

    public String getUserId() {
        return this.userId;
    }
}
