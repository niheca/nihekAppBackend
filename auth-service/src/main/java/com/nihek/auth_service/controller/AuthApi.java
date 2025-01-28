package com.nihek.auth_service.controller;

import com.nihek.auth_service.commons.constants.ApiPathConstants;
import com.nihek.auth_service.commons.dtos.UserLoginRequest;
import com.nihek.auth_service.commons.dtos.UserRegisterRequest;
import com.nihek.auth_service.commons.dtos.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.V1_PATH + ApiPathConstants.AUTH_PATH)
public interface AuthApi {

    @PostMapping(value = ApiPathConstants.REGISTER_PATH)
    public ResponseEntity<TokenResponse> registerUser(@RequestBody UserRegisterRequest userRegisterRequest);

    @PostMapping(value = ApiPathConstants.LOGIN_PATH)
    public ResponseEntity<TokenResponse> loginUser(@RequestBody UserLoginRequest userLoginRequest);

}
