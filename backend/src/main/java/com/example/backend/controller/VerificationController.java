package com.example.backend.controller;

import com.example.backend.entities.User;
import com.example.backend.entities.VerificationType;
import com.example.backend.exception.ApiRequestException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.services.VerificationService;

import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class VerificationController {
    private final VerificationService verificationService;

    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam("code") String code, @RequestParam("email") String email,
            @RequestParam("type") String type,
            @RequestParam(value = "newPassword", required = false) String newPassword) {

        try {
            VerificationType verificationType = VerificationType.valueOf(type);

            switch (verificationType) {
                case VerifyEmail:
                    verificationService.verify(code, email);
                    return ResponseEntity.ok("User verified succesfully");
                case ResetPassword:
                    verificationService.resetPassword(code, email, newPassword);
                    return ResponseEntity.ok("Password reset succesfully");
                default:
                    throw new ApiRequestException("Invalid type");
            }
        } catch (IllegalArgumentException e) {
            throw new ApiRequestException("Invalid type");
        }

    }
}
