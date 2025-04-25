package com.example.five_puzzle_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.five_puzzle_api.models.Game;
import com.example.five_puzzle_api.models.User;

@SpringBootTest
public class GameModelTest {

    @Test
    public void testGame() {
        
        int gameCount = 1000;
        String userId = "1";
        User user = new User("testUser", "testPassword", userId);
        for (int i = 0; i < gameCount; i++) {
            Game game = new Game(String.valueOf(i), userId);
            assert game.getId().equals(String.valueOf(i));
            assert game.getUserId().equals(userId);
        }        
    }

    @Test
    public void testGameState(){
        int gameCount = 1000;
        String userId = "1";
        User user = new User("testUser", "testPassword", userId);
        for (int i = 0; i < gameCount; i++) {
            Game game = new Game(String.valueOf(i), userId);
            int[][] gameState = game.getGameState();
            boolean[][] isValid = new boolean[4][4];

            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    
                    for (int l = 0; l < 16; l++) {
                        if (gameState[j][k] == l) {
                            isValid[j][k] = true;
                            break;
                        }
                        isValid[j][k] = false;
                        
                    }
                }
            }
            boolean isValidGameState = true;
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (isValid[j][k] == false) {
                        isValidGameState = false;
                        break;
                    }
                }
            }
            assert isValidGameState;
            
        }
    }

}
