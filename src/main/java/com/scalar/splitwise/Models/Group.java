package com.scalar.splitwise.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "usergroup")
@Getter
@Setter
public class Group extends BaseModel{
    
    private String name;

    @ManyToMany
    private List<User> members;
    
    @ManyToOne
    private User createdBy;

    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;
}
