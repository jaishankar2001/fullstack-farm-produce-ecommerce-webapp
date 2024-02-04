package com.example.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.services.VerificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class VerificationController {
    private final VerificationService verificationService;

    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam("code") String code, @RequestParam("email") String email) {
        verificationService.verify(code, email);
        return ResponseEntity.ok("User verified succesfully");
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
