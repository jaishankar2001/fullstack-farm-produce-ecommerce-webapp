package com.example.backend.services;

<<<<<<< HEAD
import com.example.backend.dto.request.RefreshTokenRequest;
import com.example.backend.dto.request.SignInRequest;
import com.example.backend.dto.request.SignUpRequest;
import com.example.backend.dto.response.JwtAuthenticationResponse;
=======
import com.example.backend.dto.request.SignUpRequest;
>>>>>>> 54a6bb6c909c10a24cc66aa4cc064847a90b8442
import com.example.backend.entities.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
<<<<<<< HEAD

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
=======
>>>>>>> 54a6bb6c909c10a24cc66aa4cc064847a90b8442
}
