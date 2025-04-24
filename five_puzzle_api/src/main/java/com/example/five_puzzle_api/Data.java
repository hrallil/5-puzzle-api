package com.example.five_puzzle_api;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.example.five_puzzle_api.models.Game;
import com.example.five_puzzle_api.models.User;

@Component
public class Data {
    // since the hashmaps act like the "database" ive made the whole Data class act like a repository  
    private final HashMap<String,User> users;
    private final HashMap<String, Game> games;

    public Data() {
        this.users = new HashMap<>();
        this.games = new HashMap<>();
    }
    public HashMap<String, User> getUsers() {
        return users;
    }
    public void addUsers(User user) {
        users.put(user.getId(), user);
    }
    
    public HashMap<String, Game> getGames() {
        return games;
    }

    public void addGames(Game game) {
        games.put(game.getId(), game);
    }

    public void removeGame(String id) {
        games.remove(id);
    }

    public void removeUser(String id) {
        users.remove(id);
    }

    public void updateUser(String id, User user) {
        users.put(id, user);
    }

    public void updateGame(String id, Game game) {
        games.put(id, game);
    }

    public User getUser(String id) {
        return users.get(id);
    }

    public Game getGame(String id) {
        return games.get(id);
    }

    public int getGameCount() {
        return games.size();
    }

    public int getUserCount() {
        return users.size();
    }
}
