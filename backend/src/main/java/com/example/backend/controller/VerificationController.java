package com.example.backend.controller;

import com.example.backend.entities.User;
import com.example.backend.entities.VerificationType;
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
    public ResponseEntity<String> verify(@RequestParam("code") String code, @RequestParam("email") String email, @RequestParam("type") String type, @RequestBody String newPassword) {
        String message= "invalid type";
        if(Objects.equals(type, "VerifyEmail")) {
            verificationService.verify(code, email);
            message = "User verified succesfully";

        } else if (Objects.equals(type, "ResetPassword")) {
            verificationService.resetPassword(code, email, newPassword);
            message = "Password reset succesfully";
        }
        return ResponseEntity.ok(message);
        // } catch (Exception e) {
        // boolean alreadyVerified = "User is already verified".equals(e.getMessage());
        // String extraMessage = alreadyVerified ? "<p>Please <a href='/auth/login'>log
        // in</a> to continue.</p>"
        // : "<p>Please <a href='/resend-verification?email=" + email
        // + "'>resend the verification email</a> and try again.</p>";
        // responseBody = Head + "</head><body><h1>Email verification failed</h1><p>" +
        // e.getMessage() + ".</p>"
        // + extraMessage + "</body></html>";
        // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        // }
    }
}
