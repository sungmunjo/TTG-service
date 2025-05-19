package com.kr.moon.talktogether.auth.service;

import com.kr.moon.talktogether.auth.JwtProvider;
import com.kr.moon.talktogether.auth.model.LoginRequest;
import com.kr.moon.talktogether.auth.model.LoginResponse;
import com.kr.moon.talktogether.user.entity.Users;
import com.kr.moon.talktogether.user.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsersRepository usersRepository;
    private final JwtProvider jwtProvider;

    public LoginResponse login(LoginRequest input){
        LoginResponse output = new LoginResponse();

        Users user = usersRepository.findUserByUserEmail(input.userEmail());
        System.out.println("[" + input.userEmail() + "]");
        if(user.getUserEmail() == null || user.getUserEmail().equals("")){
            throw new RuntimeException("There is not User");
        }

        if(!user.getPassword().equals(input.password())){
            throw new RuntimeException("Password is wrong");
        }
        output.setToken(jwtProvider.createToken(user.getUserEmail(), user.getRole()));
        return output;
    }


}
