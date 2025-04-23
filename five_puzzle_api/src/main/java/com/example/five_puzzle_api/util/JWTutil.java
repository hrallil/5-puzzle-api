package com.example.five_puzzle_api.util;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTutil {
    @Value("${jwt.key}") private String SECRET_KEY;
    private final long EXPIRATION_TIME = 36000000; // 1 hour in milliseconds

    
    public String generateToken(String username) {
        System.out.println("Generating token for user: " + username + "with key" + SECRET_KEY);
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + EXPIRATION_TIME);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        return token;
    }
}
