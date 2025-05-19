package com.kr.moon.talktogether.user.user_info.controller;

import com.kr.moon.talktogether.user.user_info.service.get_user_list.UserInfoGetUserList;
import com.kr.moon.talktogether.user.user_info.service.get_user_list.UserInfoGetUserListOut;
import com.kr.moon.talktogether.user.user_info.service.post_save_user.UserInfoSaveUserInfo;
import com.kr.moon.talktogether.user.user_info.service.post_save_user.UserInfoSaveUserInfoIn;
import com.kr.moon.talktogether.user.user_info.service.post_save_user.UserInfoSaveUserInfoOut;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class AccountInfoController {

    private final UserInfoSaveUserInfo userInfoSaveUserInfo;
    private final UserInfoGetUserList userInfoGetUserList;
    public AccountInfoController(UserInfoSaveUserInfo userInfoSaveUserInfo, UserInfoGetUserList userInfoGetUserList) {
        this.userInfoSaveUserInfo = userInfoSaveUserInfo;
        this.userInfoGetUserList = userInfoGetUserList;
    }

    @PostMapping
    public UserInfoSaveUserInfoOut save(@RequestBody UserInfoSaveUserInfoIn input) {
        return userInfoSaveUserInfo.saveAccount(input);
    }

    @GetMapping
    public UserInfoGetUserListOut getAll() {
        return userInfoGetUserList.getAll();
    }
}