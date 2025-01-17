package com.example.backend.services;

import java.security.Principal;
import java.util.*;

import com.example.backend.entities.Wallet;

public interface WalletService {

    double checkBalance(int userId);

    List<Wallet> gethistory(Principal principal);

    void addMoney(String email, double amount);

    String createPaymentIntent(String amount, Principal principal);

    // void spendMoney(int payerId, int receiverId, double amount);

}
