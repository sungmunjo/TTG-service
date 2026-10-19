package com.kr.moon.talktogether.auth.model;

import com.kr.moon.talktogether.user.entity.Users;
import lombok.Data;

@Data
public class RegisterResponse {
    private Users userInfo;
}
