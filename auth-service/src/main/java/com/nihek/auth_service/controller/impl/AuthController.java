package com.nihek.auth_service.controller.impl;

import com.nihek.auth_service.commons.dtos.UserLoginRequest;
import com.nihek.auth_service.commons.dtos.UserRegisterRequest;
import com.nihek.auth_service.commons.dtos.TokenResponse;
import com.nihek.auth_service.controller.AuthApi;
import com.nihek.auth_service.service.AuthService;
import com.nihek.auth_service.service.impl.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    public ResponseEntity<TokenResponse> registerUser(UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.ok(authService.registerUser(userRegisterRequest));
    }

    public ResponseEntity<TokenResponse> loginUser(UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(authService.loginUser(userLoginRequest));
    }
}
