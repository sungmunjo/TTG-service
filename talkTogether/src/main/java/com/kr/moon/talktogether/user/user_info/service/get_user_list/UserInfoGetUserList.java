package com.kr.moon.talktogether.user.user_info.service.get_user_list;

import com.kr.moon.talktogether.user.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UserInfoGetUserList {
    private final UsersRepository usersRepository;

    public UserInfoGetUserList(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserInfoGetUserListOut getAll(){
        UserInfoGetUserListOut output = new UserInfoGetUserListOut();
        output.setUserList(usersRepository.findAll());

        return output;
    }
}
