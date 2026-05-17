package com.bank.dto;

import lombok.Data;

@Data
public class DepositRequest {

    private int userId;
    private double amount;
}