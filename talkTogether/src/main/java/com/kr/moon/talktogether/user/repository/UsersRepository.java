package com.kr.moon.talktogether.user.repository;

import com.kr.moon.talktogether.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    public Users findUserByUserEmail(String userEmail);
}
