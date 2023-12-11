package com.scalar.splitwise.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scalar.splitwise.Models.ExpenseUser;
import com.scalar.splitwise.Models.User;

@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser, Long>{

    List<ExpenseUser> findAllByUser(User user);
    
}
