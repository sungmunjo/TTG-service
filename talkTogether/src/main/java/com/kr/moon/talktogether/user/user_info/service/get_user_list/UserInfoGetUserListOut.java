package com.kr.moon.talktogether.user.user_info.service.get_user_list;


import com.kr.moon.talktogether.user.entity.Users;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoGetUserListOut {
    private List<Users> userList;
}
