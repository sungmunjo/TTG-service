package com.kr.moon.talktogether.user.user_info.service.post_save_user;

import com.kr.moon.talktogether.user.entity.Users;
import com.kr.moon.talktogether.user.repository.UsersRepository;
import org.springframework.stereotype.Service;
@Service
public class UserInfoSaveUserInfo {

    private final UsersRepository usersRepository;

    public UserInfoSaveUserInfo(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public UserInfoSaveUserInfoOut saveAccount(UserInfoSaveUserInfoIn input){
        UserInfoSaveUserInfoOut output = new UserInfoSaveUserInfoOut();

        Users user = new Users();
        user.setUserEmail(input.getUserEmail());
        user.setUserName(input.getName());
        user.setPassword(input.getPassword());
        user.setRole(input.isAdmin() ? "ADMIN" : "CUSTOMER");
        usersRepository.save(user);

        output.setUserInfo(user);
        return output;
    }
}
