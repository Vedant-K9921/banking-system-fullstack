package com.bank.service;

import com.bank.entity.User;
import com.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.entity.Account;
import com.bank.repository.AccountRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    public User register(User user) {

    User savedUser = userRepository.save(user);

    Account account = new Account();
    account.setBalance(0);
    account.setUser(savedUser);

    accountRepository.save(account);

    return savedUser;
}

    public User login(String email, String password) {

    User user = userRepository.findByEmail(email);

    if(user != null &&
       user.getPassword().equals(password)) {

        return user;
    }

    return null;
}
}
