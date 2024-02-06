package com.example.backend.services.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.backend.entities.VerificationType;
import org.springframework.stereotype.Service;

import com.example.backend.entities.User;
import com.example.backend.entities.UserMeta;
import com.example.backend.entities.VerificationCode;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.UserMetaRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.VerificationCodeRepository;
import com.example.backend.services.EmailService;
import com.example.backend.services.VerificationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationServiceImplementation implements VerificationService {

    // private final UserMeta userMeta;
    private final UserRepository userRepository;
    private final UserMetaRepository userMetaRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;

    @Override
    public void verify(String code, String email) {
        // User user = userRepository.findByEmail(email).orElseThrow();
        User user = userRepository.findByEmail(email);
        UserMeta userMeta = userMetaRepository.findByUser(user);
        if (user == null) {
            throw new ApiRequestException("Not able to find user");
        }

        if (userMeta.isVerified()) {
            throw new ApiRequestException("User is already verified");
        }

        VerificationCode verificationCode = verificationCodeRepository.findByCodeAndEmail(code, email);
        if (verificationCode == null) {
            throw new ApiRequestException("Invalid verification code");
        }

        if (verificationCode.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new ApiRequestException("Verification code has expired");
        }

        userMeta.setVerified(true);
        userRepository.save(user);
        verificationCodeRepository.delete(verificationCode);
    }

    @Override
    public void resetPassword(String code, String email, String newPassword){
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ApiRequestException("Not able to find user");
        }
        VerificationCode verificationCode = verificationCodeRepository.findByCodeAndEmail(code, email);
        if (verificationCode == null) {
            throw new ApiRequestException("Invalid verification code");
        }

        if (verificationCode.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new ApiRequestException("Verification code has expired");
        }
        user.setPassword(newPassword);
        userRepository.save(user);
        verificationCodeRepository.delete(verificationCode);
    }

    @Override
    public void sendVerificationEmail(User user, VerificationType type) {
        UserMeta userMeta = userMetaRepository.findByUser(user);
        if (user == null) {
            throw new ApiRequestException("User not found!");
        }

        if (userMeta != null && userMeta.isVerified() && type != VerificationType.valueOf("ResetPassword")) {
            throw new ApiRequestException("User already verified");
        }

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(user.getEmail());
        if (verificationCode != null && verificationCode.getExpiryTime().isBefore(LocalDateTime.now())) {
            verificationCodeRepository.delete(verificationCode);
            verificationCode = null;
        }

        if (verificationCode == null) {
            String code = UUID.randomUUID().toString();
            LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(30);
            verificationCode = new VerificationCode(code, user.getEmail(), expiryTime, type);
            verificationCodeRepository.save(verificationCode);
        }

        String url = "http://localhost:3000/verify-email?email=%s&code=%s&type=%s";
        String verificationUrl = String.format(
                url,
                user.getEmail(),
                verificationCode.getCode(),
                verificationCode.getVerificationType());
        if(type == VerificationType.valueOf("VerifyEmail")) {
            String subject = "Verify your email";
            String body = "Please click on this link to verify your email: " + verificationUrl;
            emailService.sendEmail(user.getEmail(), subject, body);
        } else if (type == VerificationType.valueOf("ResetPassword")) {
            String subject = "Reset your password";
            String body = "Please click on this link to reset your Password: " + verificationUrl;
            emailService.sendEmail(user.getEmail(), subject, body);
        }
    }
}
