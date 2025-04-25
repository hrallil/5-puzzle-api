package com.example.five_puzzle_api;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.five_puzzle_api.models.Game;
import com.example.five_puzzle_api.models.Move;
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
    
    @Test
    public void testGameMove(){
        String userId = "1";
        User user = new User("testUser", "testPassword", userId);
        Game game = new Game("1", userId);
        game.applyMove(2,3);
        game.applyMove(3,3);
    }

    @Test
    public void testGameMove2(){
        String userId = "1";
        User user = new User("testUser", "testPassword", userId);
        Game game = new Game("1", userId);
        game.applyMove(2,3);
        List<Move> moves = game.getMoves();
        assert moves.size() == 1;
        assert moves.get(0).getX1() == 3;
        assert moves.get(0).getY1() == 3;
        assert moves.get(0).getX2() == 2;
        assert moves.get(0).getY2() == 3;
    }
    @Test
    public void testGameMove3(){
        String userId = "1";
        User user = new User("testUser", "testPassword", userId);
        Game game = new Game("1", userId);
        game.applyMove(2,3);
        ArrayList<Move> moves = game.getMoves();
        for (int i = moves.size()-1; i >= 0; i--) {
            game.applyMove(moves.get(i).getX1(), moves.get(i).getY1());
        }
        assert game.getIsSolved();
    }

    @Test
    public void testGameMove4(){
        String userId = "1";
        User user = new User("testUser", "testPassword", userId);
        Game game = new Game("1", userId);
        game.applyMove(2,3);
        game.applyMove(2,2);
        game.applyMove(2,1);
        game.applyMove(2,0);
        game.applyMove(2,1);
        game.applyMove(1,1);
        game.applyMove(1,2);
        game.applyMove(1,3);
        ArrayList<Move> moves = game.getMoves();
        assert moves.size() == 8;
        for (int i = moves.size()-1; i >= 0; i--) {
            game.applyMove(moves.get(i).getX1(), moves.get(i).getY1());
        }
        assert game.getIsSolved();
    }
}
