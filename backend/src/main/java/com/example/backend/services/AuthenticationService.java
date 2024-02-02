package com.example.backend.services;

import com.example.backend.dto.request.SignUpRequest;
import com.example.backend.entities.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
}
