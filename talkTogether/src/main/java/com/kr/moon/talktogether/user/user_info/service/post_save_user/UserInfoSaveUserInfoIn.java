package com.kr.moon.talktogether.user.user_info.service.post_save_user;


import lombok.Data;

@Data
public class UserInfoSaveUserInfoIn {
    private String name;
    private String userEmail;
    private String password;
    private boolean isAdmin = false;
}
