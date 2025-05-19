package com.kr.moon.talktogether.auth;

import com.kr.moon.talktogether.user.user_info.service.get_user_info.UserInfoGetUserInfo;
import com.kr.moon.talktogether.user.user_info.service.get_user_info.UserInfoGetUserInfoIn;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserInfoGetUserInfo userInfoGetUserInfo;
    public JwtAuthFilter(JwtProvider jwtProvider, UserInfoGetUserInfo userInfoGetUserInfo) {
        this.jwtProvider = jwtProvider;
        this.userInfoGetUserInfo = userInfoGetUserInfo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = jwtProvider.resolveToken(request);
        if (token != null && jwtProvider.validateToken(token)) {
            String email = jwtProvider.getUsernameFromToken(token);

            UserInfoGetUserInfoIn reqIn = new UserInfoGetUserInfoIn();
            reqIn.setEmail(email);

            UserDetails userDetails = userInfoGetUserInfo.getUserInfoByEmail(reqIn).getUserDetails();

            var auth = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

}