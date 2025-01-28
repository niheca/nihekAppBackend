package com.nihek.user_service.controller;

import com.nihek.user_service.commons.constants.ApiPathConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.V1_PATH+ApiPathConstants.USER_PATH)
public interface UserApi {

    @DeleteMapping(ApiPathConstants.DELETE_USER_PATH)
    public ResponseEntity<String> deleteUser(@RequestHeader("Authorization") String authHeader);

    @PatchMapping(ApiPathConstants.UPDATE_ACCOUNT_PRIVACITY_PATH)
    public ResponseEntity<String> updateAccountPrivacity(@RequestHeader("Authorization") String authHeader);

    @PatchMapping(ApiPathConstants.UPDATE_NICKNAME_PATH)
    public ResponseEntity<String> updateNickname(@RequestHeader("Authorization") String authHeader,
                                                 @RequestParam(value = "nickname") String nickname);

    @PostMapping(ApiPathConstants.FOLLOW_PATH)
    public ResponseEntity<String> followUser(@RequestHeader("Authorization") String authHeader,
                                             @RequestParam String nickname);

    @DeleteMapping(ApiPathConstants.DELETE_FOLLOWER_PATH)
    public ResponseEntity<String> deleteFollower(@RequestHeader("Authorization") String authHeader,
                                                 @RequestParam String nickname);

    @DeleteMapping(ApiPathConstants.DELETE_FOLLOW_PATH)
    public ResponseEntity<String> deleteFollow(@RequestHeader("Authorization") String authHeader,
                                               @RequestParam String nickname);
}
