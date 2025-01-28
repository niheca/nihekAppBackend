package com.nihek.user_service.service.impl;

import com.nihek.commons.jwt.JwtUtils;
import com.nihek.commons.models.UserModel;
import com.nihek.commons.repositories.UserRepository;
import com.nihek.user_service.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public String deleteUser(String authHeader) {
        String token = authHeader.substring(7);
        Long userId = Long.valueOf(jwtUtils.extractedUserId(token));
        try{
            userRepository.deleteById(userId);
            return "User deleted";
        }
        catch(Exception e){
            return "Error deleting user ID:" + userId;
        }
    }

    @Override
    public String updateAccountPrivacity(String authHeader) {
        String token = authHeader.substring(7);
        Long userId = Long.valueOf(jwtUtils.extractedUserId(token));
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with that ID not found"));
        if(user.isAccountPublic()){
            user.setAccountPublic(false);
            userRepository.save(user);
            return "Public -> Private ";
        }
        else{
            user.setAccountPublic(true);
            userRepository.save(user);
            return "Private -> Public";
        }
    }

    @Override
    public String updateNickname(String authHeader,String nickname) {
        String token = authHeader.substring(7);
        Long userId = Long.valueOf(jwtUtils.extractedUserId(token));
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with that ID not found"));
        user.setNickname(nickname);
        userRepository.save(user);
        return "Nickname updated";
    }

    @Override
    public String followUser(String authHeader, String nickname) {
        String token = authHeader.substring(7);
        Long userId = Long.valueOf(jwtUtils.extractedUserId(token));
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with that ID not found"));
        UserModel follower = userRepository.findByNickname(nickname).orElseThrow(() -> new RuntimeException("Follower with that ID not found"));
        user.getFollows().add(follower);
        follower.getFollowers().add(user);
        userRepository.save(user);
        userRepository.save(follower);

        return "User " + follower.getNickname() + " followed";
    }

    @Override
    public String deleteFollower(String authHeader,String nickname) {
        String token = authHeader.substring(7);
        Long userId = Long.valueOf(jwtUtils.extractedUserId(token));
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with that ID not found"));
        UserModel follower = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("Follower with that ID not found"));
        user.getFollowers().remove(follower);
        follower.getFollows().remove(user);
        userRepository.save(user);
        userRepository.save(follower);

        return "You deleted your follower with nickname " + follower.getNickname();
    }

    @Override
    public String deleteFollow(String authHeader,String nickname) {
        String token = authHeader.substring(7);
        Long userId = Long.valueOf(jwtUtils.extractedUserId(token));
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with that ID not found"));
        UserModel follower = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("Follower with that ID not found"));

        user.getFollows().remove(follower);
        follower.getFollowers().remove(user);
        userRepository.save(user);
        userRepository.save(follower);

        return "You unfollow the user with nickname" + follower.getNickname();
    }

}
