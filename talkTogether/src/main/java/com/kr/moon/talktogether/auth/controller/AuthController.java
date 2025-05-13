package com.kr.moon.talktogether.auth.controller;

import com.kr.moon.talktogether.auth.model.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 로그인 로직 (예: DB 조회, 비밀번호 검사 등)
        String jwt = jwtProvider.createToken(request.getEmail());
        return ResponseEntity.ok(Map.of("token", jwt));
    }
}