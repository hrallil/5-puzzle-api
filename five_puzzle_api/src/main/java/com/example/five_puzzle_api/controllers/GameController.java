package com.example.five_puzzle_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.five_puzzle_api.models.Game;
import com.example.five_puzzle_api.models.User;
import com.example.five_puzzle_api.requests.MoveRequest;
import com.example.five_puzzle_api.services.GameService;
import com.example.five_puzzle_api.services.UserService;

@RestController
public class GameController {
    
    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @GetMapping("/get/game/{gameId}")
    public ResponseEntity<?> getGame(@PathVariable String gameId) {
        try {

            Game game = gameService.getGame(gameId);

            return ResponseEntity.ok(game);
        } catch (Exception e) {
            if (e.getMessage().contains("Game not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Game creation failed with error: " + e.getMessage());
        }
    }


    @PostMapping("/move/game")
    public ResponseEntity<?> makeMove(@RequestBody MoveRequest moveRequest) {
        try{
            // Parse the move from the request body
            String gameId = moveRequest.getGameId(); // pull from the request body
            int moveX = moveRequest.getX();
            int moveY = moveRequest.getY();
            Game game = gameService.applyMove(gameId, moveX, moveY);
            return ResponseEntity.ok(game);

        } catch (Exception e) {
            if (e.getMessage().contains("Game not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found");
            }
            if (e.getMessage().contains("Invalid move")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid move");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Move application failed with error: " + e.getMessage());

        }
    }

    @PostMapping("/create/game")
    public ResponseEntity<?> createGame(@RequestBody User userRequest) {
        try{
            String username = userRequest.getUsername();
            System.out.println(username);
            User user = userService.getUserFromUsername(userRequest.getUsername());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            Game game = gameService.createGame(user);
            return ResponseEntity.ok(game);
        } catch (Exception e) {
            if (e.getMessage().contains("User not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Game creation failed with error: " + e.getMessage());
        }
    }
}
