package com.example.backend;

//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

//import org.junit.Before;
//import org.junit.Test;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.backend.dto.request.ResetPasswordRequest;
import com.example.backend.dto.request.SignInRequest;
import com.example.backend.dto.request.SignUpRequest;
import com.example.backend.dto.response.LoginResponse;
import com.example.backend.entities.User;
import com.example.backend.entities.UserMeta;
import com.example.backend.entities.VerificationType;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.UserMetaRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.JWTService;
import com.example.backend.services.VerificationService;
import com.example.backend.services.impl.AuthenticationServiceImpl;

public class AuthenticationServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMetaRepository userMetaRepository;

    @Mock
    private VerificationService verificationService;

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSignUp_Successful() {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("test@example.com");
        signUpRequest.setFirstName("Drashti");
        signUpRequest.setLastName("Patel");
        signUpRequest.setPassword("123");
        // signUpRequest.setRole("User_role");

        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // Act
        User result = authenticationService.signUp(signUpRequest);

        // Assert
        assertEquals(signUpRequest.getEmail(), result.getEmail());
        assertEquals(signUpRequest.getFirstName(), result.getFirstname());
        assertEquals(signUpRequest.getLastName(), result.getLastname());
        assertEquals(signUpRequest.getRole(), result.getRole());

        // Verify userRepository.save is called once
        verify(userRepository, times(1)).save(any(User.class));

        // Verify userMetaRepository.save is called once
        verify(userMetaRepository, times(1)).save(any(UserMeta.class));

        // Verify verificationService.sendVerificationEmail is called once with correct
        // arguments
        verify(verificationService, times(1)).sendVerificationEmail(any(User.class), eq(VerificationType.VerifyEmail));
    }

    @Test
    public void testSignUp_UserAlreadyRegistered() {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("test@example.com");

        when(userRepository.findByEmail(anyString())).thenReturn(new User());

        // Act and Assert
        ApiRequestException exception = assertThrows(ApiRequestException.class, () -> {
            authenticationService.signUp(signUpRequest);
        });
        assertEquals("User already registered", exception.getMessage());
    }

    @Test
    public void testSignIn_Successful() {
        // Arrange
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail("test@example.com");
        signInRequest.setPassword("123");

        User tempUser = new User();
        tempUser.setEmail(signInRequest.getEmail());
        tempUser.setPassword(BCrypt.hashpw(signInRequest.getPassword(), BCrypt.gensalt()));

        when(userRepository.findByEmail(anyString())).thenReturn(tempUser);

        UserMeta userMeta = new UserMeta();
        userMeta.setVerified(true);
        userMeta.setWallet_balance(100.0); // Set any desired value

        when(userMetaRepository.findByUser(any(User.class))).thenReturn(userMeta);

        when(jwtService.generateToken(any(User.class))).thenReturn("mockToken");
        when(jwtService.generateRefreshToken(anyMap(), any(User.class))).thenReturn("mockRefreshToken");

        // Act
        LoginResponse loginResponse = authenticationService.signIn(signInRequest);

        // Assert
        assertEquals("test@example.com", loginResponse.getEmail());
        assertEquals("mockToken", loginResponse.getToken());
        assertEquals("mockRefreshToken", loginResponse.getRefreshToken());
    }

    @Test
    public void testForgotPassword_UserExists() {
        // Arrange
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setEmail("test@example.com");

        User user = new User();

        when(userRepository.findByEmail(anyString())).thenReturn(user);

        // Act
        String result = authenticationService.forgotPassword(resetPasswordRequest);

        // Assert
        assertEquals("Please check email for password reset link", result);

        // Verify that verification email was sent
        verify(verificationService, times(1)).sendVerificationEmail(user, VerificationType.ResetPassword);
    }

    @Test
    public void testForgotPassword_UserNotFound() {
        // Arrange
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setEmail("nonexistent@example.com");

        when(userRepository.findByEmail(anyString())).thenReturn(null);

        // Act and Assert
        ApiRequestException exception = assertThrows(ApiRequestException.class, () -> {
            authenticationService.forgotPassword(resetPasswordRequest);
        });
        assertEquals("User doesn't exist", exception.getMessage());
    }
}