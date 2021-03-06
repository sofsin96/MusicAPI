package com.example.gateway.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class JWTUtil {
    public Jws<Claims> getAllClaimsFromToken(String authToken) {
        //Change secret to signing key from auth services.
        return  Jwts.parser()
                .setSigningKey("ICantTellYou#IDontRemember#YouHaveToGuessIt#WhosAsking#LongEnough".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(authToken);
    }
}
