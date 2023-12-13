package com.scalar.splitwise.Strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

import com.scalar.splitwise.Models.Expense;
import com.scalar.splitwise.Models.ExpenseUser;
import com.scalar.splitwise.Models.User;
import com.scalar.splitwise.Services.Transaction;

public class TwoHeapStrategy implements SettleUpStrategy {

    @Override
    public List<Transaction> settle(List<Expense> expenses) {
        /*
         * 1. for all expenses find out who owes or gets what ?
         * 2. from this Map apply two heap strategy.
         */

        Map<User, Integer> userExpenses = new ConcurrentHashMap<>();

        for (Expense expense : expenses) {
            if (expense != null && expense.getExpenseUsers() != null) {
                for (ExpenseUser expenseUser : expense.getExpenseUsers()) {
                    if (expenseUser != null) {
                        User user = expenseUser.getUser();
                        if (user != null) {
                            int newExpense = expenseUser.getAmount();
                            userExpenses.merge(user, newExpense, Integer::sum);
                        }
                    }
                }
            }
        }

        PriorityQueue<Pair> maxHeapRecieve = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Pair> maxHeapPay = new PriorityQueue<>(Collections.reverseOrder());

        for (Map.Entry<User, Integer> entry : userExpenses.entrySet()) {
            Pair pair = new Pair(entry.getKey(), entry.getValue());
            if (entry.getValue() > 0) {
                maxHeapRecieve.add(pair);
            } else {
                maxHeapPay.add(pair);
            }
        }

        List<Transaction> result = new ArrayList<>();

        while (!maxHeapPay.isEmpty() && !maxHeapRecieve.isEmpty()) {
            Pair maxPay = maxHeapPay.poll();
            Pair maxRecieve = maxHeapRecieve.poll();

            Transaction transaction = new Transaction();

            if (maxRecieve.getAmount() > maxPay.getAmount()) {
                transaction.setAmount(maxPay.getAmount());
                transaction.setFrom(maxPay.getUser());
                transaction.setTo(maxRecieve.getUser());
                Pair balance = new Pair(maxRecieve.getUser(), maxRecieve.getAmount() - maxPay.getAmount());
                maxHeapRecieve.add(balance);
            } else if (maxRecieve.getAmount() < maxPay.getAmount()) {
                transaction.setAmount(maxRecieve.getAmount());
                transaction.setFrom(maxPay.getUser());
                transaction.setTo(maxRecieve.getUser());
                Pair balance = new Pair(maxPay.getUser(), maxPay.getAmount() - maxRecieve.getAmount());
                maxHeapPay.add(balance);
            } else {
                transaction.setAmount(maxPay.getAmount());
                transaction.setFrom(maxPay.getUser());
                transaction.setTo(maxRecieve.getUser());
            }

            result.add(transaction);
        }

        return result;
    }

}

class Pair implements Comparable<Pair> {
    private final User user;
    private final int amount;

    public Pair(User user, int amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Pair other) {
        // Compare pairs based on amount
        return Integer.compare(this.amount, other.amount);
    }
}
