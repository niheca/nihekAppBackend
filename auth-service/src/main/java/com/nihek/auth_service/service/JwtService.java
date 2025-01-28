package com.nihek.auth_service.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateToken(Long userId);
    boolean isExpired(String token);
    Claims getClaims(String token);
    Integer extractedUserId(String token);
}
