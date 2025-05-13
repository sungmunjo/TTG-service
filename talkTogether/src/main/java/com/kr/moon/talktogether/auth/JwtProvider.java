package com.kr.moon.talktogether.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class JwtProvider {

    private final String secretKey = "yourSecret";

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        // 토큰 유효성 검사 로직 (예: 서명 검증, 만료일 등)
        return true;
    }

    public UserDetails getUserDetails(String token) {
        // 토큰에서 username 파싱 후 사용자 정보 반환
        return new com.kr.moon.talktogether.entity.Account("user@example.com", "", List.of());
    }
}