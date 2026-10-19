package com.kr.moon.talktogether.auth.service;

import com.kr.moon.talktogether.auth.JwtProvider;
import com.kr.moon.talktogether.auth.entity.RefreshToken;
import com.kr.moon.talktogether.auth.model.LoginRequest;
import com.kr.moon.talktogether.auth.model.LoginResponse;
import com.kr.moon.talktogether.auth.repository.RefreshTokenRepository;
import com.kr.moon.talktogether.user.entity.Users;
import com.kr.moon.talktogether.user.repository.UsersRepository;
import com.kr.moon.talktogether.auth.model.RegisterRequest;
import com.kr.moon.talktogether.auth.model.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsersRepository usersRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder passwordEncoder;
    public RegisterResponse register(RegisterRequest input) {
        RegisterResponse output = new RegisterResponse();

        Users user = new Users();
        user.setUserEmail(input.getUserEmail());
        user.setUserName(input.getName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(input.isAdmin() ? "ADMIN" : "CUSTOMER");
        usersRepository.save(user);

        output.setUserInfo(user);
        return output;
    }

    @Transactional
    public LoginResponse login(LoginRequest input) {
        Users user = usersRepository.findUserByUserEmail(input.userEmail());
        if (user == null || user.getUserEmail() == null) {
            throw new RuntimeException("There is not User");
        }
        if (!passwordEncoder.matches(input.password(), user.getPassword())) {
            throw new RuntimeException("Password is wrong");
        }

        // 기존 토큰 삭제 (단일 로그인 유지 시) — 멀티 기기 허용하려면 이 줄 제거
        refreshTokenRepository.deleteByUser(user);

        String rawToken = jwtProvider.createRefreshToken();
        Date expiresAt = jwtProvider.getRefreshTokenExpiresAt();
        refreshTokenRepository.save(new RefreshToken(user, rawToken, expiresAt));

        LoginResponse output = new LoginResponse();
        output.setAccessToken(jwtProvider.createToken(user.getUserEmail(), user.getRole()));
        output.setRefreshToken(rawToken);
        return output;
    }

    @Transactional
    public String refresh(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refreshToken.getExpiresAt().before(new Date())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }

        // Refresh Token Rotation
        String newRawToken = jwtProvider.createRefreshToken();
        refreshToken.setToken(newRawToken);
        refreshToken.setExpiresAt(jwtProvider.getRefreshTokenExpiresAt());
        refreshTokenRepository.save(refreshToken);

        Users user = refreshToken.getUser();
        return jwtProvider.createToken(user.getUserEmail(), user.getRole());
    }

    @Transactional
    public void logout(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
        refreshTokenRepository.delete(refreshToken);
    }
}
