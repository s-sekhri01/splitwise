package com.scalar.splitwise.Services;

import com.scalar.splitwise.Models.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private int amount;
    private User from;
    private User to;
}
