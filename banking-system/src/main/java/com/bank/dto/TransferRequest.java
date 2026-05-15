package com.bank.dto;

import lombok.Data;

@Data
public class TransferRequest {

    private int senderId;
    private int receiverId;
    private double amount;
}