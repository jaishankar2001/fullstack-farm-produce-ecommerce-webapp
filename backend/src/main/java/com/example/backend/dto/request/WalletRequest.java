package com.example.backend.dto.request;

import java.util.Date;
import lombok.Data;

@Data
public class WalletRequest {
    private int userId;
    private double amount;
    private Date date;
}
