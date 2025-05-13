package com.kr.moon.talktogether.repository;

import com.kr.moon.talktogether.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

    public Account findAccountByUsernameContainingIgnoreCase(String username);
}
