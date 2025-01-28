package com.nihek.auth_service.service.impl;
import com.nihek.commons.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {

        UserDetails userDetails = (UserDetails) userRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException(nickname));

        return userDetails;
    }
}
