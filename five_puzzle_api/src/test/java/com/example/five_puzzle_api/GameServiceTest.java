package com.example.five_puzzle_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.five_puzzle_api.models.Game;
import com.example.five_puzzle_api.models.User;
import com.example.five_puzzle_api.security.JWTService;
import com.example.five_puzzle_api.services.GameService;

@SpringBootTest
class GameServiceTest {

    private Data dataMock;
    private JWTService jwtUtilMock;
    private GameService gameService;
	
    @BeforeEach
    public void setup(){
        dataMock = new Data();
        jwtUtilMock = new JWTService();
        gameService = new GameService(dataMock);
        String username = "testUser";
        String password = "testPassword";
        dataMock.addUsers(new User(username, password, "1"));
        dataMock.addGames(new Game("1", "1"));
    }

    @Test
    void testGetGame() throws Exception {
        String gameId = "1";
        Game game = gameService.getGame(gameId);
        assert(game != null);
        assert(game.getId().equals(gameId));
    }
    
    @Test
    void testCreateGame() throws Exception {
        User user = dataMock.getUsers().get("1");
        Game game = gameService.createGame(user);
        String gameId = game.getId();
        assert(gameId != null);
        assert(dataMock.getGames().get(gameId) != null);
        assert(dataMock.getGames().get(gameId).getUserId().equals(user.getId()));
    }


}