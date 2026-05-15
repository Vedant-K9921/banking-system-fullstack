package com.bank.controller;

import com.bank.entity.User;
import com.bank.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return userService.register(user);
    }

    @PostMapping("/login")
public User login(@RequestBody User user) {

    return userService.login(
            user.getEmail(),
            user.getPassword()
    );
}
}