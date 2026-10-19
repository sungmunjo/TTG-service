package com.kr.moon.talktogether.auth.model;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String userEmail;
    private String password;
    private boolean isAdmin = false;
}
