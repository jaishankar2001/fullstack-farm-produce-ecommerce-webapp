package com.example.backend.Controller;

import com.example.backend.controller.VerificationController;
import com.example.backend.services.VerificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class VerificationControllerTest {
    @Mock
    private VerificationService verificationService;

    @InjectMocks
    private VerificationController verificationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testverifyUser() {
        String code = "verification_code";
        String email = "user@example.com";
        String type = "email_verification";
        String newPassword = "new_password";

        // ARRANGE
        String message = "Verification successful";
        when(verificationService.verifyAndUpdate(code, email, newPassword, type)).thenReturn(message);

        ResponseEntity<String> responseEntity = verificationController.verify(code, email, type, newPassword);

        // Verify response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(message, responseEntity.getBody());

    }
}
