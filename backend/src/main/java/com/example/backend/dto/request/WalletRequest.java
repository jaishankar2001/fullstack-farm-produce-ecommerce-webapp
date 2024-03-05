package com.example.backend.dto.request;

import lombok.Data;

@Data
public class WalletRequest {
    private String email;
    private Double amount;
}
