package com.example.backend.services;

import com.example.backend.entities.User;
import com.example.backend.entities.VerificationType;

public interface VerificationService {
    void verify(String code, String email);
    void resetPassword(String code, String email, String newPassword);
    void sendVerificationEmail(User user, VerificationType type);
}
