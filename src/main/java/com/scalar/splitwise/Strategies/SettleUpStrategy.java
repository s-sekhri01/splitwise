package com.scalar.splitwise.Strategies;

import java.util.List;

import com.scalar.splitwise.Models.Expense;
import com.scalar.splitwise.Services.Transaction;

public interface SettleUpStrategy {
    public List<Transaction>settle(List<Expense> expenses);
}
