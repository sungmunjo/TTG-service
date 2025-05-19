package com.kr.moon.talktogether.user.user_info.service.get_user_info;

import com.kr.moon.talktogether.auth.model.CustomUserDetails;
import com.kr.moon.talktogether.user.entity.Users;
import com.kr.moon.talktogether.user.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UserInfoGetUserInfo {
    private final UsersRepository usersRepository;

    public UserInfoGetUserInfo(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserInfoGetUserInfoOut getUserInfoByEmail(UserInfoGetUserInfoIn input){
        UserInfoGetUserInfoOut output = new UserInfoGetUserInfoOut();

        Users user = usersRepository.findUserByUserEmail(input.getEmail());
        output.setUserDetails(new CustomUserDetails(user));
        return output;
    }
}
