package com.example.five_puzzle_api.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.five_puzzle_api.Data;

@Service
public class GameService {
    private final Data data;

    @Autowired
    public GameService(Data data){
        this.data = data;
    }

    public void createGame(String username) {

        
    }
}
