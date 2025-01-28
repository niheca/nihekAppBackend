package com.nihek.auth_service.service.impl;

import com.nihek.auth_service.commons.constants.TopicConstants;
import com.nihek.auth_service.commons.dtos.UserLoginRequest;
import com.nihek.auth_service.commons.dtos.UserRegisterRequest;
import com.nihek.auth_service.commons.dtos.TokenResponse;
import com.nihek.auth_service.service.AuthService;
import com.nihek.commons.models.UserModel;
import com.nihek.commons.repositories.UserRepository;
import org.apache.kafka.common.quota.ClientQuotaAlteration;
import org.apache.kafka.streams.processor.To;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtServiceImpl jwtService;
    private final PasswordEncoder encoder;
    private final StreamBridge streamBridge;

    public AuthServiceImpl(UserRepository userRepository, JwtServiceImpl jwtService, PasswordEncoder encoder, StreamBridge streamBridge) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.encoder = encoder;
        this.streamBridge = streamBridge;
    }

    @Override
    public TokenResponse registerUser(UserRegisterRequest userRegisterRequest) {
       return Optional.of(userRegisterRequest).map(this::registerRequestToEntity)
               .filter(user -> userRepository.findByNickname(user.getNickname()).isEmpty())
               .filter(user -> userRepository.findByEmail(userRegisterRequest.getEmail()).isEmpty())
               .map(user -> userRepository.save(user))
               .map(this::sendUserRegisteredEvent)
               .orElseThrow(() -> new RuntimeException("Error creating User"));
    }

    private TokenResponse sendUserRegisteredEvent(UserModel userModel) {
        streamBridge.send(TopicConstants.USER_REGISTERED_TOPIC,userModel.getEmail());
        return Optional.of(userModel)
                .map(user -> TokenResponse.builder()
                        .access_token(jwtService.generateToken(user.getId()))
                        .build()
                )
                .orElseThrow(() -> new RuntimeException("Error creating User"));
    }

    @Override
    public TokenResponse loginUser(UserLoginRequest userLoginRequest) {
        return Optional.of(userLoginRequest)
                .flatMap(user -> userRepository.findByEmail(userLoginRequest.getEmail()))
                .filter(user -> encoder.matches(userLoginRequest.getPassword(), user.getPassword()))
                .map(user -> TokenResponse.builder()
                        .access_token(jwtService.generateToken(user.getId()))
                        .build()
                )
                .orElseThrow(() -> new RuntimeException("Error Logging User"));
    }
    private UserModel registerRequestToEntity(UserRegisterRequest userRegisterRequest) {
        return UserModel.builder()
                .email(userRegisterRequest.getEmail())
                .password(encoder.encode(userRegisterRequest.getPassword()))
                .nickname(userRegisterRequest.getNickname())
                .accountPublic(false)
                .role("ROLE_USER")
                .build();
    }
    private UserModel loginRequestToEntity(UserLoginRequest userRegisterRequest) {
        return UserModel.builder()
                .email(userRegisterRequest.getEmail())
                .password(encoder.encode(userRegisterRequest.getPassword()))
                .build();
    }
}
