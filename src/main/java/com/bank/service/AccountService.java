package com.bank.service;

import com.bank.dto.DepositRequest;
import com.bank.dto.TransferRequest;
import com.bank.dto.WithdrawRequest;

import com.bank.entity.Account;
import com.bank.entity.Transaction;
import com.bank.entity.User;

import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    // =========================
    // DEPOSIT
    // =========================

    public String deposit(DepositRequest request) {

        User user =
                userRepository.findById(
                        request.getUserId()
                ).orElse(null);

        if (user == null) {
            return "User not found";
        }

        Account account =
                accountRepository.findByUser(user);

        account.setBalance(
                account.getBalance()
                        + request.getAmount()
        );

        accountRepository.save(account);

        // SAVE TRANSACTION

        Transaction transaction =
                new Transaction();

        transaction.setType("Deposit");

        transaction.setAmount(
                request.getAmount()
        );

        transaction.setUser(user);

        transactionRepository.save(transaction);

        return "Deposit successful!";
    }

    // =========================
    // WITHDRAW
    // =========================

    public String withdraw(WithdrawRequest request) {

        User user =
                userRepository.findById(
                        request.getUserId()
                ).orElse(null);

        if (user == null) {
            return "User not found";
        }

        Account account =
                accountRepository.findByUser(user);

        if (account.getBalance()
                < request.getAmount()) {

            return "Insufficient balance";
        }

        account.setBalance(
                account.getBalance()
                        - request.getAmount()
        );

        accountRepository.save(account);

        // SAVE TRANSACTION

        Transaction transaction =
                new Transaction();

        transaction.setType("Withdraw");

        transaction.setAmount(
                request.getAmount()
        );

        transaction.setUser(user);

        transactionRepository.save(transaction);

        return "Withdrawal successful!";
    }

    // =========================
    // TRANSFER
    // =========================

    @Transactional
    public String transfer(
            TransferRequest request
    ) {

        User sender =
                userRepository.findById(
                        request.getSenderId()
                ).orElse(null);

        User receiver =
                userRepository.findById(
                        request.getReceiverId()
                ).orElse(null);

        if (sender == null
                || receiver == null) {

            return "Invalid users";
        }

        Account senderAccount =
                accountRepository.findByUser(sender);

        Account receiverAccount =
                accountRepository.findByUser(receiver);

        if (senderAccount.getBalance()
                < request.getAmount()) {

            return "Insufficient balance";
        }

        // SUBTRACT MONEY

        senderAccount.setBalance(
                senderAccount.getBalance()
                        - request.getAmount()
        );

        // ADD MONEY

        receiverAccount.setBalance(
                receiverAccount.getBalance()
                        + request.getAmount()
        );

        accountRepository.save(senderAccount);

        accountRepository.save(receiverAccount);

        // SAVE TRANSACTION

        Transaction transaction =
                new Transaction();

        transaction.setType("Transfer");

        transaction.setAmount(
                request.getAmount()
        );

        transaction.setUser(sender);

        transactionRepository.save(transaction);

        return "Transfer successful!";
    }

    // =========================
    // BALANCE
    // =========================

    public String getBalance(int userId) {

        User user =
                userRepository.findById(
                        userId
                ).orElse(null);

        if (user == null) {
            return "User not found";
        }

        Account account =
                accountRepository.findByUser(user);

        return "Current Balance: ₹"
                + account.getBalance();
    }
}