package com.example.five_puzzle_api.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.five_puzzle_api.Data;
import com.example.five_puzzle_api.models.User;
import com.example.five_puzzle_api.security.JWTService;

@Service
public class UserService {
    private final Data data;
    private final JWTService jwtUtil;

    @Autowired
    public UserService(Data data, JWTService jwtUtil) {
        this.data = data;
        this.jwtUtil = jwtUtil;
    }
    
    public User createUser(User initUser) throws Exception{
        if (initUser.getUsername() == null || initUser.getPassword() == null) {
            throw new Exception("Username and password cannot be null");
        }
        if (initUser.getUsername().isEmpty() || initUser.getPassword().isEmpty()) {
            throw new Exception("Username and password cannot be empty");
        }
        if(this.getUserFromUsername(initUser.getUsername()) != null){
            throw new Exception("Username already exists with this username: " + initUser.getUsername());
        }
        String id = String.valueOf(data.getUsers().size() + 1);
        User newuser = new User(initUser.getUsername(), initUser.getPassword(), id);
        data.addUsers(newuser);
        return newuser;    
    }
    
    public String login(String username, String password) throws Exception {
        User user = this.getUserFromUsername(username); 
        if (user == null) {
            throw new Exception("User not found");
        }
        String userId = user.getId(); 
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return this.jwtUtil.generateToken(data.getUsers().get(userId).getUsername());
        }
        throw new Exception("Invalid username or password");
    }

    public User getUserFromUsername(String username) throws Exception {
        for (String id : data.getUsers().keySet()) {
            if (data.getUsers().get(id).getUsername().equals(username)) {
                return data.getUsers().get(id);
            }
        }
        return null;
    }

    public User getUserFromID(String id) throws Exception {
        User user = data.getUsers().get(id);
        if (user != null) {
            return user;
        }
        return null;
    }

    public User[] getAllUsers() {
        User[] users = new User[data.getUsers().size()];
        int i = 0;
        for (String id : data.getUsers().keySet()) {
            users[i] = data.getUsers().get(id);
            i++;
        }
        return users;
    }
}
