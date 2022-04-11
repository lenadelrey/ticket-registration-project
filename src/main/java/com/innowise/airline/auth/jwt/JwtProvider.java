package com.innowise.airline.auth.jwt;

import com.innowise.airline.model.User;
import io.jsonwebtoken.*;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {

    private static final String ACCESS_TOKEN = "accesstoken";
    private static final String REFRESH_TOKEN = "refreshtoken";

    public String generateAccessToken(@NonNull User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(accessExpiration)
                .signWith(SignatureAlgorithm.HS512, ACCESS_TOKEN)
                .claim("role", user.getRole())
                .claim("name", user.getName())
                .compact();
    }

    public String generateRefreshToken(@NonNull User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(refreshExpiration)
                .signWith(SignatureAlgorithm.HS512, REFRESH_TOKEN)
                .compact();
    }

    public boolean validateAccessToken(@NonNull String token) {
        return validateToken(token, ACCESS_TOKEN);
    }

    public boolean validateRefreshToken(@NonNull String token) {
        return validateToken(token, REFRESH_TOKEN);
    }

    private boolean validateToken(@NonNull String token, @NonNull String secret) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException |
                UnsupportedJwtException |
                MalformedJwtException |
                SignatureException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, ACCESS_TOKEN);
    }

    public Claims getRefreshClaims(@NonNull String token) {
        return getClaims(token, REFRESH_TOKEN);
    }

    private Claims getClaims(@NonNull String token, @NonNull String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

}
