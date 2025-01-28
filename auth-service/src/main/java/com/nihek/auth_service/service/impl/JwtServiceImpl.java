package com.nihek.auth_service.service.impl;

import com.nihek.auth_service.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private final String secretToken;

    public JwtServiceImpl(@Value("${jwt.secret.token}") String secretToken) {
        this.secretToken = secretToken;
    }

    @Override
    public String generateToken(Long userId) {
        Date expirationDate = new Date(Long.MAX_VALUE);

        String token =  Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretToken)
                .compact();

        return token;
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretToken)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isExpired(String token) {
        try{
            return getClaims(token).getExpiration().before(new Date());
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public Integer extractedUserId(String token) {
        try{
            return Integer.parseInt(getClaims(token).getSubject());
        }
        catch (Exception e){
            return null;
        }
    }
}
