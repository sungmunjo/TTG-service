package com.kr.moon.talktogether.user.repository;

import com.kr.moon.talktogether.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findAccountByUserNameContainingIgnoreCase(String username);
}
