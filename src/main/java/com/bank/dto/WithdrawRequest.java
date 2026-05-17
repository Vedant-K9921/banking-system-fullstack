package com.bank.dto;

import lombok.Data;

@Data
public class WithdrawRequest {

    private int userId;
    private double amount;
}
