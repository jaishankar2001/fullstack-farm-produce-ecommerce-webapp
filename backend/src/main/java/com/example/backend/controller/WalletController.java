package com.example.backend.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.backend.dto.request.WalletRequest;
import com.example.backend.dto.response.WalletResponse;
import com.example.backend.services.WalletService;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/balance/{userId}")
    public WalletResponse checkBalance(@PathVariable int userId) {
        double balance = walletService.checkBalance(userId);
        WalletResponse response = new WalletResponse();
        response.setBalance(balance);
        return response;
    }

    @PostMapping("/topup")
    @ResponseStatus(HttpStatus.CREATED)
    public void topUp(@RequestBody WalletRequest request) {
        walletService.addMoney(request.getUserId(), request.getAmount());
    }
}
