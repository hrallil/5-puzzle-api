package com.example.five_puzzle_api.util;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTutil {
    private final SignatureAlgorithm alg = SignatureAlgorithm.HS512;
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(alg);
    private final long EXPIRATION_TIME = 36000000; // 1 hour in milliseconds

    public JWTutil(){

    }

    
    public String generateToken(String username) {
        System.out.println("Generating token for user: " + username + "with key" + SECRET_KEY);
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + EXPIRATION_TIME);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY)
                .compact();
        return token;
    }
}
