package com.example.backend.Controller;

import com.example.backend.controller.AuthController;
import com.example.backend.dto.request.RefreshTokenRequest;
import com.example.backend.dto.request.ResetPasswordRequest;
import com.example.backend.dto.request.SignInRequest;
import com.example.backend.dto.request.SignUpRequest;
import com.example.backend.dto.response.JwtAuthenticationResponse;
import com.example.backend.dto.response.LoginResponse;
import com.example.backend.entities.User;
import com.example.backend.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthControllerTest {
    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignUp() {
        // Mock data
        SignUpRequest signUpRequest = new SignUpRequest();
        User user = new User();

        // ARRANGE
        when(authenticationService.signUp(signUpRequest)).thenReturn(user);

        ResponseEntity<User> responseEntity = authController.signUp(signUpRequest);

        // Verify response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    void testForgotPassword() {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        String message = "Password reset email sent successfully";

        // ARRANGE
        when(authenticationService.forgotPassword(resetPasswordRequest)).thenReturn(message);

        ResponseEntity<String> responseEntity = authController.forgotPassword(resetPasswordRequest);

        // Verify response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(message, responseEntity.getBody());
    }

    @Test
    void testSignIn() {

        SignInRequest signInRequest = new SignInRequest();
        LoginResponse loginResponse = new LoginResponse();

        when(authenticationService.signIn(signInRequest)).thenReturn(loginResponse);

        ResponseEntity<LoginResponse> responseEntity = authController.signin(signInRequest);

        // ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(loginResponse, responseEntity.getBody());
    }

    @Test
    void testRefreshToken() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        when(authenticationService.refreshToken(refreshTokenRequest)).thenReturn(jwtAuthenticationResponse);

        ResponseEntity<JwtAuthenticationResponse> responseEntity = authController.refreshToken(refreshTokenRequest);

        // ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(jwtAuthenticationResponse, responseEntity.getBody());
    }
}
