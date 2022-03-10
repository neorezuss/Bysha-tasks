package com.example.task4.security;

import com.example.task4.entity.Role;
import com.example.task4.exception.TokenValidationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.access.token.expired}")
    private long accessTokenValidityInMillis;
    @Value("${jwt.refresh.token.expired}")
    private long refreshTokenValidityInMillis;

    public String generateAccessToken(String login, List<Role> roles) {
        Date date = new Date(new Date().getTime() + accessTokenValidityInMillis);
        Claims claims = Jwts.claims().setSubject(login);
        claims.put("scopes", roles);
        return Jwts.builder()
                .setSubject(login)
                .setClaims(claims)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new TokenValidationException("Token isn't valid!");
        }
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String generateRefreshToken(String login) {
        Date date = new Date(new Date().getTime() + refreshTokenValidityInMillis);
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}