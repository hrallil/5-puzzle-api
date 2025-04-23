package com.example.five_puzzle_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.five_puzzle_api.models.User;
import com.example.five_puzzle_api.services.GameService;
import com.example.five_puzzle_api.services.UserService;

@RestController
public class Controller {
    private final UserService userService;
    private final GameService gameService;
    
    @Autowired
    public Controller(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;

    }

    // =====================================
    //             GET REQUESTS 
    // =====================================

    @GetMapping("/get/game/{gameId}")
    public String getGame(@PathVariable String gameId) {
        return "Hello, World!";
    }

    @GetMapping("/get/users")
    public String getGame() {
        // Get all users
        return "Hello, World!";
    }

    @GetMapping("/health")
    public String getHealth() {
        return "OK!";
    }

    // =====================================
    //             POST REQUESTS 
    // =====================================


    @PostMapping("/move/game")
    public String makeMove(@RequestBody String move) {
        return "Hello, World!";
    }

    @PostMapping("/create/game")
    public ResponseEntity<String> createGame(@RequestBody String username) {
        try{
            gameService.createGame(username);
            return ResponseEntity.ok("Game created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Game creation failed with error: " + e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try{
            String username = user.getUsername();
            String password = user.getPassword();
            userService.login(username, password);
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed with error: " + e.getMessage());
        }
    }

    @PostMapping("/create/user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try{
            userService.createUser(user);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User creation failed wioth error: " + e.getMessage());
        }
    }

}
