package com.example.five_puzzle_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.five_puzzle_api.models.Game;
import com.example.five_puzzle_api.models.User;

@SpringBootTest
public class DataRepositoryTest {
    
    private Data dataMock;

    @BeforeEach
    public void setup() {
        dataMock = new Data();
        String username = "testUser";
        String password = "testPassword";
        dataMock.addUsers(new User(username, password, "1"));
        dataMock.addGames(new Game("1", "1"));
    }

    @Test
    void testAddUser() {
        String username = "newUser";
        String password = "newPassword";
        dataMock.addUsers(new User(username, password, "2"));
        
        assert(dataMock.getUsers().size() == 2);
        assert(dataMock.getUsers().get("2").getUsername().equals(username));
    }

    @Test
    void testAddManyUser() {
        int userCount = 10;
        for (int i = 0; i <= userCount; i++) {
            String username = "user" + (i + 1);
            String password = "password" + (i + 1);
            dataMock.addUsers(new User(username, password, String.valueOf(i + 1)));
        }
        
        assert(dataMock.getUserCount() == userCount + 1);
        for (int i = 0; i <= userCount; i++) {
            String username = "user" + (i + 1);
            assert(dataMock.getUsers().get(String.valueOf(i + 1)).getUsername().equals(username));
        }
    }

    @Test
    void testGetUser() {
        String userId = "1";
        User user = dataMock.getUsers().get(userId);
        
        assert(user != null);
        assert(user.getUsername().equals("testUser"));
    }

    @Test
    void testGetUserNotFound() {
        String userId = "nonExistentUser";
        User user = dataMock.getUsers().get(userId);
        
        assert(user == null);
    }

    @Test
    void testRemoveUser(){
        String userId = "1";
        dataMock.removeUser(userId);
        
        assert(dataMock.getUsers().isEmpty());
        assert(dataMock.getUsers().get(userId) == null);
    }

    @Test
    void testRemoveUserNotFound(){
        String userId = "nonExistentUser";
        dataMock.removeUser(userId);
        
        assert(dataMock.getUsers().size() == 1);
        assert(dataMock.getUsers().get("1").getUsername().equals("testUser"));
    }



    @Test
    void testGetGame(){
        String gameId = "1";
        Game game = dataMock.getGames().get(gameId);
        
        assert(game != null);
        assert(game.getId().equals("1"));
    }
    @Test
    void testGetGameNotFound(){
        String gameId = "nonExistentGame";
        Game game = dataMock.getGames().get(gameId);
        
        assert(game == null);
    }
    @Test
    void testRemoveGame(){
        String gameId = "1";
        dataMock.removeGame(gameId);
        
        assert(dataMock.getGames().isEmpty());
        assert(dataMock.getGames().get(gameId) == null);
    }
    @Test
    void testRemoveGameNotFound(){
        String gameId = "nonExistentGame";
        dataMock.removeGame(gameId);
        
        assert(dataMock.getGames().size() == 1);
        assert(dataMock.getGames().get("1").getId().equals("1"));
    }
    @Test
    void testAddGame(){
        String gameId = "2";
        dataMock.addGames(new Game(gameId, "1"));
        
        assert(dataMock.getGames().size() == 2);
        assert(dataMock.getGames().get(gameId).getId().equals(gameId));
    }

    @Test
    void testAddManyGame(){
        String userId = "1";
        int gameCount = 10;
        for (int i = 0; i <= gameCount; i++) {
            String gameId = String.valueOf(i + 1);
            dataMock.addGames(new Game(gameId, userId));
        }
        
        System.out.println(dataMock.getGames().size());
        assert(dataMock.getGameCount() == 11);
        for (int i = 0; i <= gameCount; i++) {
            String gameId = String.valueOf(i + 1);
            assert(dataMock.getGames().get(gameId).getId().equals(gameId));
        }
    }
}
