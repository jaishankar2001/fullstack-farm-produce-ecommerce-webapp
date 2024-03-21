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
import com.example.backend.dto.response.LoginResponse;
import com.example.backend.entities.User;
import com.example.backend.services.AuthenticationService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/ResetPasswordReq")
    public ResponseEntity<String> forgotPassword(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {
        return ResponseEntity.ok(authenticationService.forgotPassword(resetPasswordRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> signin(@RequestBody @Valid SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(
            @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
