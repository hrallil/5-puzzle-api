package com.example.five_puzzle_api.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.five_puzzle_api.Data;
import com.example.five_puzzle_api.models.Game;
import com.example.five_puzzle_api.models.User;

@Service
public class GameService {
    private final Data data;

    @Autowired
    public GameService(Data data){
        this.data = data;
    }

    public Game createGame(User user) {
        // Create a new game for the user
        String gameId = String.valueOf(data.getGames().size() + 1);
        User userFromData = this.data.getUser(user.getId());
        if (userFromData == null) {
            throw new RuntimeException("User not found");
        }
        Game newGame = new Game(gameId, user.getId());
        data.addGames(newGame);
        return newGame;
    }

    public Game getGame(String gameId) {
        Game game = data.getGame(gameId);
        if (game == null) {
            throw new RuntimeException("Game not found");
        }
        return game;
    }

    public Game applyMove(String gameId, int moveX, int moveY) {
        // Apply the move to the game
        Game game = data.getGame(gameId);
        if (game != null) {
            game.applyMove(moveX, moveY);
            return game;
        }else{
            throw new RuntimeException("Game not found");
        }
    }
}
