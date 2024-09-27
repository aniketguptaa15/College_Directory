package com.college_directory.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String SECRET_KEY; // Secret key loaded from environment or properties

    private final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 60 * 60; // Token validity period in seconds (5 hours)

    // Method to extract username from JWT token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Method to extract claims from JWT token
    private <T> T extractClaim(String token, ClaimsResolver<T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.resolve(claims);
    }

    // Method to get all claims from JWT token
    private Claims getAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8)) // Explicit charset
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.error("Error while parsing JWT token: {}", e.getMessage());
            throw new JwtException("Invalid JWT token");
        }
    }

    // Method to generate JWT token
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8)) // Explicit charset
                .compact();
    }

    // Method to validate JWT token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Method to check if JWT token is expired
    private boolean isTokenExpired(String token) {
        try {
            return getAllClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            logger.warn("Error while checking token expiration: {}", e.getMessage());
            return true; // Treat invalid or malformed tokens as expired
        }
    }

    @FunctionalInterface
    private interface ClaimsResolver<T> {
        T resolve(Claims claims);
    }

    // Custom exception class for JWT related issues
    public static class JwtException extends RuntimeException {
        public JwtException(String message) {
            super(message);
        }
    }
}
