package com.bank.repository;

import com.bank.entity.Account;
import com.bank.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository
        extends JpaRepository<Account, Integer> {

    Account findByUser(User user);
}
