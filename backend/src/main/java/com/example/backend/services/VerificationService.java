package com.example.backend.services;

import com.example.backend.entities.User;

public interface VerificationService {
    void verify(String code, String email);

    void sendVerificationEmail(User user);
}
