package com.example.backend.services.impl;

import com.example.backend.repository.WalletRepository;
import com.example.backend.services.WalletService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import lombok.RequiredArgsConstructor;

import com.example.backend.entities.User;
import com.example.backend.entities.UserMeta;
import com.example.backend.entities.Wallet;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.UserMetaRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserMetaRepository userMeta;
    private final UserRepository userRepository;
    @Value("${stripe.apikey}")
    private String stripeAPI;

    @Value("${api.endpoint}")
    private String APIendpoint;

    @Value("${frontend.endpoint}")
    private String frontendEndpoint;

    public double checkBalance(int userId) {
        UserMeta walletBalanceById = userMeta.findById(userId);
        double myBalance = 0.0;
        if (walletBalanceById != null) {
            myBalance = walletBalanceById.getWallet_balance();
        }
        return myBalance;
    }

    public void addMoney(String email, double amount) {
        // Retrieving the user's wallet info.
        User user = userRepository.findByEmail(email);

        UserMeta userInfo = user.getUserMeta();
        Wallet amountInfo = new Wallet();
        if (userInfo != null) {
            double updatedBalance = userInfo.getWallet_balance() + amount;
            userInfo.setWallet_balance(updatedBalance);
            amountInfo.setAmount(amount);
            amountInfo.setUser(user);
            amountInfo.setPaymnent_Method_Reference("Stripe");
            userMeta.save(userInfo);
            walletRepository.save(amountInfo);
        } else {
            System.out.println("OOPS: Wallet Info not found for the pertaining user: " + email);
        }
    }

    public String createPaymentIntent(String amount, Principal principal) {
        try {

            System.out.println("GERR?");
            System.out.println(principal.getName());
            String successURL = APIendpoint + "/wallet/topup?amount=" + Double.parseDouble(amount)
                    + "&email="
                    + principal.getName();

            String failureURL = frontendEndpoint + "/payment/fail";

            Stripe.apiKey = stripeAPI;

            SessionCreateParams params = SessionCreateParams.builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setCancelUrl(failureURL)
                    .setSuccessUrl(successURL)
                    .addLineItem(SessionCreateParams.LineItem.builder()
                            .setQuantity(1L)
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("cad")
                                            .setUnitAmount((long) Integer.parseInt(amount) * 100)
                                            .setProductData(
                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                            .setName("Ecopick Wallet")
                                                            .build())
                                            .build())
                            .build())
                    .build();

            Session s = Session.create(params);
            return s.getId();
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    public List<Wallet> gethistory(Principal principal) {
        try {
            User user = userRepository.findByEmail(principal.getName());
            List<Wallet> wallet = walletRepository.findAllByUserId(user.getId(),
                    Sort.by(Sort.Direction.DESC, "createdAt"));
            return wallet;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
