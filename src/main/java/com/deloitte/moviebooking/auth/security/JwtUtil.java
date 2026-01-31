package com.deloitte.moviebooking.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * JwtUtil is responsible for generating and validating JWT tokens.
 *
 * It encapsulates all JWT-related operations such as:
 * - Token creation
 * - Token validation
 * - Claim extraction
 */
@Component
public class JwtUtil {

    /**
     * Secret key used to sign JWT tokens.
     * Must be sufficiently long for HMAC-SHA algorithms.
     */
    private static final String SECRET =
            "movie-booking-secret-key-must-be-long-enough";

    /**
     * Token validity duration (1 hour).
     */
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    /**
     * Generates a JWT token containing userId and role.
     *
     * @param userId authenticated user's ID
     * @param role user's role (USER / ADMIN)
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

    /**
     * Validates a JWT token and parses its claims.
     *
     * @param token JWT token
     * @return parsed claims
     */
    public Jws<Claims> validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException ex) {
            throw new RuntimeException("Invalid or expired JWT token");
        }
    }

    /**
     * Extracts userId from JWT token.
     */
    public String extractUserId(String token) {
        return validateToken(token).getBody().getSubject();
    }

    /**
     * Extracts user role from JWT token.
     */
    public String extractRole(String token) {
        return validateToken(token).getBody().get("role", String.class);
    }
}
