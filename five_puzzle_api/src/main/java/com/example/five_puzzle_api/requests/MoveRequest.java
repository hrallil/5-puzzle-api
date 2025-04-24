package com.example.five_puzzle_api.requests;

public class MoveRequest {
    private final int x;
    private final int y;
    private final String gameId;

    public MoveRequest(int x, int y, String gameId) {
        this.x = x;
        this.y = y;
        this.gameId = gameId;

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getGameId() {
        return this.gameId;
    }
    
}
