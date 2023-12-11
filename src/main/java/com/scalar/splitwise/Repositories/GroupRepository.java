package com.scalar.splitwise.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scalar.splitwise.Models.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{
    
}
