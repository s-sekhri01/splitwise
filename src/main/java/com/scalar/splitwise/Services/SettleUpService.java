package com.scalar.splitwise.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalar.splitwise.Models.Expense;
import com.scalar.splitwise.Models.ExpenseUser;
import com.scalar.splitwise.Models.Group;
import com.scalar.splitwise.Models.User;
import com.scalar.splitwise.Repositories.ExpenseUserRepository;
import com.scalar.splitwise.Repositories.GroupRepository;
import com.scalar.splitwise.Repositories.UserRepository;
import com.scalar.splitwise.Strategies.SettleUpStrategy;


@Service
public class SettleUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseUserRepository expenseUserRepository;

    private SettleUpStrategy settleUpStrategy;

    private GroupRepository groupRepository;

    public List<Transaction> settleUpUser(Long userId){
        /*
         * 1. Get all the expenses this user is part of
         * 2. Iterate through all the expenses and find out all the people involved 
         *    and how much is owed/ owes by each.
         * 3. Use min and max heap algo to calculate the list of transactions
         * 4. return the list of transactions.
         */

        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException();
        }
        User user = userOptional.get();

        List<ExpenseUser> expenseUsers = expenseUserRepository.findAllByUser(user);

        List<Expense> expenses = new ArrayList<>();
        for(ExpenseUser expenseUser : expenseUsers){
            expenses.add(expenseUser.getExpense());
        }

        List<Transaction> myTransactions = new ArrayList<>();
        List<Transaction> transactions = settleUpStrategy.settle(expenses);
        for(Transaction transaction : transactions){
            if(transaction.getFrom().equals(user) || transaction.getTo().equals(user)){
                myTransactions.add(transaction);
            }
        }
        return myTransactions;
    }

    public List<Transaction> settleUpGroup(Long groupId){

        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if(groupOptional.isEmpty()){
            throw new RuntimeException();
        }

        Group group = groupOptional.get();
        List<Expense> expenses = group.getExpenses();
        List<Transaction> transactions = settleUpStrategy.settle(expenses);
        return transactions;
    }
}
