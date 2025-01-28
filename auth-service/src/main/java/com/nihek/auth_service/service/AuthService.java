package com.nihek.auth_service.service;

import com.nihek.auth_service.commons.dtos.UserLoginRequest;
import com.nihek.auth_service.commons.dtos.UserRegisterRequest;
import com.nihek.auth_service.commons.dtos.TokenResponse;
import org.springframework.stereotype.Service;

public interface AuthService {
    public TokenResponse registerUser(UserRegisterRequest userRegisterRequest);
    public TokenResponse loginUser(UserLoginRequest userLoginRequest);
}
