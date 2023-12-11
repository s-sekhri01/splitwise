package com.scalar.splitwise.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scalar.splitwise.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
