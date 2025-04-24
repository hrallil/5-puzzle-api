package com.example.five_puzzle_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiscController {

    @GetMapping("/health")
    public String getHealth() {
        return "OK!";
    }
    @GetMapping("/info")
    public String getInfo() {
        return "15 Puzzle API - Version 1.0";
    }
}
