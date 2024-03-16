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

        String message = verificationService.verifyAndUpdate(code, email, newPassword, type);
        return ResponseEntity.ok(message);

    }
}
