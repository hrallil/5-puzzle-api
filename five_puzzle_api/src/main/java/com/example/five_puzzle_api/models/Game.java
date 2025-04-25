package com.example.five_puzzle_api.models;

import java.util.ArrayList;

public class Game {
    private final String id; // string as the id should be able to be unique, for now i have it as a counter
    private final String userId; // to see what user is associated with the game
    private final int[][] gameState; // sat to string to begin with, will be changed to some 2d array later on
    private final int boardSize = 4; // size of the board, 4x4 for the 15 puzzle
    private final ArrayList<Move> moves = new ArrayList<>();

    public Game(String id, String userId) {
        this.id = id;
        this.userId = userId;
        this.gameState = initGameState(); 
    }

    private int[][] initGameState() {
        
        int[][] initialState = {
            {1, 2, 3, 4},
            {5, 6, 7, 8}, 
            {9, 10, 11, 12},
            {13, 14, 15, 0} 
        };
        return shuffleGameState(initialState, 100);
    }

    public int[][] shuffleGameState(int[][] gameState, int shuffleCount) {
        for (int i = 0; i < shuffleCount; i++) {
            int x1 = (int) (Math.random() * boardSize);
            int y1 = (int) (Math.random() * boardSize);
            int x2 = (int) (Math.random() * boardSize);
            int y2 = (int) (Math.random() * boardSize);

           gameState = swapTiles(gameState, x1, y1, x2, y2);
           moves.add(new Move(x1, y1, x2, y2));
        }
        return gameState; 
    }

    public int[][] swapTiles(int[][] state, int x1, int y1, int x2, int y2) {
        int temp = state[x1][y1];
        state[x1][y1] = state[x2][y2];
        state[x2][y2] = temp;
        return state;
    }

    public boolean isSolved(){
        int[][] solvedState = {
            {1, 2, 3, 4},
            {5, 6, 7, 8}, 
            {9, 10, 11, 12},
            {13, 14, 15, 0} 
        };
        for (int i = 0; i < this.gameState.length; i++) {
            for (int j = 0; j < this.gameState[i].length; j++) {
                if (gameState[i][j] != solvedState[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidMove(int moveX, int moveY) {
        // Check if the move is within the bounds of the game state
        
        boolean withinBoard = (moveX >= 0 && moveX < boardSize && moveY >= 0 && moveY < boardSize);
        
        boolean isNextToZero = false;
        if (moveY-1 >= 0            && gameState[moveX][moveY - 1] == 0) isNextToZero = true;
        if (moveY+1 < boardSize    && gameState[moveX][moveY + 1] == 0) isNextToZero = true;
        if (moveX-1 >= 0            && gameState[moveX - 1][moveY] == 0) isNextToZero = true;
        if (moveX+1 < boardSize    && gameState[moveX + 1][moveY] == 0) isNextToZero = true;
        return withinBoard && isNextToZero;
    }

    public Game applyMove(int moveX, int moveY) {
        if (isValidMove(moveX, moveY)) {
            int zeroX = -1;
            int zeroY = -1;

            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (gameState[i][j] == 0) {
                        zeroX = i;
                        zeroY = j;
                    }
                }
            }

            gameState[zeroX][zeroY] = gameState[moveX][moveY];
            gameState[moveX][moveY] = 0;
            moves.add(new Move(zeroX, zeroY, moveX, moveY));
        }else{
            throw new IllegalArgumentException("Invalid move");
        }

        return this;
    }

    public String getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public int[][] getGameState() {
        return this.gameState;
    }    
}
