package com.example.five_puzzle_api.security;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTService {
    private final SignatureAlgorithm alg = SignatureAlgorithm.HS512;
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(alg);
    private final long EXPIRATION_TIME = 36000000; // 1 hour in milliseconds
    
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

    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
