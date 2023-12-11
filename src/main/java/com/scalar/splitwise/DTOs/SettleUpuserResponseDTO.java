package com.scalar.splitwise.DTOs;

import java.util.List;

import com.scalar.splitwise.Services.Transaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettleUpuserResponseDTO {
    private List<Transaction> transactions;
}
