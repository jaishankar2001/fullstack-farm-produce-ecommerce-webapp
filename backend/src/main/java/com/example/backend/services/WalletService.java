package com.example.backend.services;

import java.util.*;

public interface WalletService {

    double checkBalance(int userId);

    void addMoney(int userId, double amount);
    // void spendMoney(int payerId, int receiverId, double amount);

}
