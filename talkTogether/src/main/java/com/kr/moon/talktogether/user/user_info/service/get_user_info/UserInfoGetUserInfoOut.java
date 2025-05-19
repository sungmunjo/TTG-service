package com.kr.moon.talktogether.user.user_info.service.get_user_info;


import com.kr.moon.talktogether.auth.model.CustomUserDetails;
import lombok.Data;

@Data
public class UserInfoGetUserInfoOut {
    private CustomUserDetails userDetails;
}
