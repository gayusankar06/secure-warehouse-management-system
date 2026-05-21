package com.warehouse.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET =
            "mysecretkeymysecretkeymysecretkey";

    private final Key key =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes()
            );

    // GENERATE TOKEN
    public String generateToken(
            String email,
            String role
    ) {

        Map<String, Object> claims =
                new HashMap<>();

        claims.put("role", role);

        return Jwts.builder()

                .setClaims(claims)

                .setSubject(email)

                .setIssuedAt(
                        new Date()
                )

                .setExpiration(

                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )

                )

                .signWith(key)

                .compact();
    }

    // EXTRACT EMAIL
    public String extractUsername(
            String token
    ) {

        return extractClaims(token)
                .getSubject();
    }

    // EXTRACT ROLE
    public String extractRole(
            String token
    ) {

        return extractClaims(token)
                .get("role", String.class);
    }

    // EXTRACT CLAIMS
    private Claims extractClaims(
            String token
    ) {

        return Jwts.parserBuilder()

                .setSigningKey(key)

                .build()

                .parseClaimsJws(token)

                .getBody();
    }
}