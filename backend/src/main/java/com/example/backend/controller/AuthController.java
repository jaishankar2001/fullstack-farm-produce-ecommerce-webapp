package com.example.backend.controller;

import com.example.backend.dto.request.ResetPasswordRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.backend.dto.request.RefreshTokenRequest;
import com.example.backend.dto.request.SignInRequest;
import com.example.backend.dto.request.SignUpRequest;
import com.example.backend.dto.response.JwtAuthenticationResponse;
import com.example.backend.dto.response.StripeResponse;
import com.example.backend.entities.User;
import com.example.backend.services.AuthenticationService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/create-payment-intent")
    public ResponseEntity<Map> createPaymentIntent(@RequestParam("amount") String amount) throws StripeException {

        String successURL = "http://localhost:8080/api/auth/paymentsuccess?amount=100&email=123@gmail.com";

        String failureURL = "http://localhost:3000/payment/fail";

        Stripe.apiKey = "sk_test_51J0PUySIJjtkSpgkwLWSNz2rkkS3FtCOBl9XYeFzCKHrQncLQJws9FodTsc1hL9apjqkGUOuIArdeEkDlxDkJUds004LPirNvP";

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
        System.out.println(s.getId());

        Map<String, Object> response = new HashMap<>();
        response.put("sessionId", s.getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
        // return ResponseEntity.ok(s.getId());
        // return ResponseEntity.ok("h1?");
    }

    @GetMapping("/demo")
    public ResponseEntity<String> customerHome() {
        return ResponseEntity.ok("Hi Customer");
    }

    @GetMapping("/paymentsuccess")
    public RedirectView paymentsuccess(@RequestParam("amount") String amount, @RequestParam("email") String email) {

        System.out.println(amount + " " + email);
        return new RedirectView("http://localhost:3000/payment/success");
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/ResetPasswordReq")
    public ResponseEntity<String> forgotPassword(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {
        return ResponseEntity.ok(authenticationService.forgotPassword(resetPasswordRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody @Valid SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(
            @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
