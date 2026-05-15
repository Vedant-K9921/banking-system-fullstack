package com.bank.controller;

import com.bank.dto.DepositRequest;
import com.bank.dto.TransferRequest;
import com.bank.dto.WithdrawRequest;
import com.bank.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/deposit")
    public String deposit(
            @RequestBody DepositRequest request
    ) {

        return accountService.deposit(request);
    }
    @PostMapping("/withdraw")
    public String withdraw(
        @RequestBody WithdrawRequest request
    ) {

    return accountService.withdraw(request);
    }
    @PostMapping("/transfer")
    public String transfer(
        @RequestBody TransferRequest request
    ) {

    return accountService.transfer(request);
    }
    @GetMapping("/balance/{userId}")
    public String getBalance(
        @PathVariable int userId
    ) {

    return accountService.getBalance(userId);
    }
}
