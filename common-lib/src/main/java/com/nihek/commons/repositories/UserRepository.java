package com.nihek.commons.repositories;

import com.nihek.commons.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    Optional<UserModel> findByNickname(String nickname);
    Optional<UserModel> findByEmail(String email);
}
