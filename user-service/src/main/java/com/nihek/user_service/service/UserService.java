package com.nihek.user_service.service;

import com.nihek.user_service.commons.constants.ApiPathConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService {

    public String deleteUser(String authHeader);

    public String updateAccountPrivacity(String authHeader);

    public String updateNickname(String authHeader,String nickname);

    public String followUser(String authHeader,String nickname);

    public String deleteFollower(String authHeader,String idFollower);

    public String deleteFollow(String authHeader, String nickname);
}
