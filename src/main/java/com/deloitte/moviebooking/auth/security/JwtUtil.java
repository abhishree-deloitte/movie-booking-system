package com.deloitte.moviebooking.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Utility class for generating JWT tokens.
 */
@Component
public class JwtUtil {

    // Secret key used to sign JWT tokens
    // Must be at least 256 bits for HS256
    private static final String SECRET =
            "movie-booking-secret-key-that-is-long-enough";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    /**
     * Generates a JWT token containing userId and role.
     *
     * @param userId user identifier
     * @param role user role (USER / ADMIN)
     * @return signed JWT token
     */
    public String generateToken(String userId, String role) {

        return Jwts.builder()
                .setSubject(userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
