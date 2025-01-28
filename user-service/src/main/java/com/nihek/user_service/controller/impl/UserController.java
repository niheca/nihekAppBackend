package com.nihek.user_service.controller.impl;

import com.nihek.user_service.controller.UserApi;
import com.nihek.user_service.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<String> deleteUser(String authHeader) {
        return ResponseEntity.ok().body(userService.deleteUser(authHeader));
    }

    @Override
    public ResponseEntity<String> updateAccountPrivacity(String authHeader) {
        return ResponseEntity.ok(userService.updateAccountPrivacity(authHeader));
    }

    @Override
    public ResponseEntity<String> updateNickname(String authHeader,String nickname) {
        return ResponseEntity.ok(userService.updateNickname(authHeader,nickname));
    }

    @Override
    public ResponseEntity<String> followUser(String authHeader, String nickname) {
        return ResponseEntity.ok(userService.followUser(authHeader,nickname));
    }

    @Override
    public ResponseEntity<String> deleteFollower(String authHeader, String nickname) {
        return ResponseEntity.ok(userService.deleteFollower(authHeader,nickname));
    }

    @Override
    public ResponseEntity<String> deleteFollow(String authHeader, String nickname) {
        return ResponseEntity.ok(userService.deleteFollow(authHeader,nickname));
    }
}
