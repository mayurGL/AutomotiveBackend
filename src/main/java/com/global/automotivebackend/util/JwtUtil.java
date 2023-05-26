package com.global.automotivebackend.util;

import com.global.automotivebackend.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JwtUtil {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final ConcurrentHashMap<String, User> tokenMap = new ConcurrentHashMap<>();


    public static String generateToken(User user){

        String token = Jwts.builder()
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .signWith(SECRET_KEY)
                .compact();

        tokenMap.put(token, user);
        return token;
    }


    public static User validateToken(String token){

        return tokenMap.get(token);
    }


    public static void invalidateToken(String token){
        tokenMap.remove(token);
    }
}
