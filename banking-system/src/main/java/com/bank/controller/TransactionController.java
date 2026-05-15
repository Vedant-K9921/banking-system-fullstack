package com.bank.controller;

import com.bank.entity.Transaction;
import com.bank.entity.User;
import com.bank.repository.TransactionRepository;
import com.bank.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("*")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public List<Transaction> getTransactions(
            @PathVariable int userId
    ) {

        User user =
                userRepository.findById(userId)
                        .orElse(null);

        return transactionRepository.findByUser(user);
    }
}