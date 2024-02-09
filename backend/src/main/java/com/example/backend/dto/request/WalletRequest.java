package com.example.backend.dto.request;

import lombok.Data;

@Data
public class WalletRequest {
    private int userId;
    private double amount;
}
