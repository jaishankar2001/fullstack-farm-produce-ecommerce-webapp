package com.example.backend.controller;

import com.example.backend.dto.request.ResetPasswordRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity<StripeResponse> createPaymentIntent(@RequestBody String price) {

        String successURL = "http://localhost:3000/payment/success";

        String failureURL = "http://localhost:3000/payment/failed";

        Stripe.apiKey = "sk_test_51J0PUySIJjtkSpgkwLWSNz2rkkS3FtCOBl9XYeFzCKHrQncLQJws9FodTsc1hL9apjqkGUOuIArdeEkDlxDkJUds004LPirNvP";

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successURL)
                .setCancelUrl(failureURL)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                // Provide the exact Price ID (for example, pr_1234) of the product you want to
                                // sell
                                .setPrice("{{PRICE_ID}}")
                                .build())
                .build();
        Session session = Session.create(params);

        StripeResponse stripeResponse = new StripeResponse(price);
        return ResponseEntity.ok(stripeResponse);
    }

    @GetMapping("/demo")
    public ResponseEntity<String> customerHome() {
        return ResponseEntity.ok("Hi Customer");
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
