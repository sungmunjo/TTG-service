package com.kr.moon.talktogether.user.user_info.controller;

import com.kr.moon.talktogether.user.user_info.service.get_user_list.UserInfoGetUserList;
import com.kr.moon.talktogether.user.user_info.service.get_user_list.UserInfoGetUserListOut;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserInfoController {

    private final UserInfoGetUserList userInfoGetUserList;

    public UserInfoController(UserInfoGetUserList userInfoGetUserList) {
        this.userInfoGetUserList = userInfoGetUserList;
    }

    @GetMapping
    public UserInfoGetUserListOut getAll() {
        return userInfoGetUserList.getAll();
    }


}