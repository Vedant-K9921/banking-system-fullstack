package com.bank.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    private double balance;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
