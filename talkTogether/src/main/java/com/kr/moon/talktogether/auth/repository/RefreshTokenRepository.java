package com.kr.moon.talktogether.auth.repository;

import com.kr.moon.talktogether.auth.entity.RefreshToken;
import com.kr.moon.talktogether.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(Users user);
}
