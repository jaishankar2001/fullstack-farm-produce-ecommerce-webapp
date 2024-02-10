package com.example.backend.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import lombok.RequiredArgsConstructor;

import com.example.backend.dto.request.WalletRequest;
import com.example.backend.dto.response.WalletHistoryResponse;
import com.example.backend.dto.response.WalletResponse;
import com.example.backend.entities.Wallet;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.services.WalletService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/create-payment-intent")
    public ResponseEntity<Map> createPaymentIntent(@RequestParam("amount") String amount, Principal principal) {
        try {
            String sesisonId = walletService.createPaymentIntent(amount, principal);
            Map<String, Object> response = new HashMap<>();
            response.put("sessionId", sesisonId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/balance/{userId}")
    public WalletResponse checkBalance(@PathVariable int userId) {
        double balance = walletService.checkBalance(userId);
        WalletResponse response = new WalletResponse();
        response.setBalance(balance);
        return response;
    }

    @GetMapping("/topup")
    public RedirectView topUp(@ModelAttribute WalletRequest request) {
        try {
            walletService.addMoney(request.getEmail(), request.getAmount());
            return new RedirectView("http://localhost:3000/payment/success");
        } catch (Exception e) {
            return new RedirectView("http://localhost:3000/payment/success");
        }
    }

    @GetMapping("/history")
    public ResponseEntity<List<Wallet>> walletHistory(Principal principal) {
        return ResponseEntity.ok(walletService.gethistory(principal));
    }

}
