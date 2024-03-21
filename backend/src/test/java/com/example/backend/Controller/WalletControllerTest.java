package com.example.backend.Controller;

import com.example.backend.controller.WalletController;
import com.example.backend.dto.request.WalletRequest;
import com.example.backend.entities.Wallet;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.services.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WalletControllerTest {

    @Value("${frontend.endpoint}")
    private String frontendEndpoint;
    @Mock
    private WalletService walletService;

    @InjectMocks
    private WalletController walletController;

    private Principal principal;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        principal = mock(Principal.class);
    }

    @Test
    public void paymentIntentSuccess() {
        String amount = "100";
        String sessionId = "some_session_id";

        // ARRANGE
        when(walletService.createPaymentIntent(amount, principal)).thenReturn(sessionId);

        ResponseEntity<Map> responseEntity = walletController.createPaymentIntent(amount, principal);

        // Verify response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("sessionId", sessionId);
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testCreatePaymentIntentFailure() {
        // Mock data
        String amount = "100";

        // ARRANGE for failure scenario
        when(walletService.createPaymentIntent(amount, principal))
                .thenThrow(new ApiRequestException("Failed to create payment intent"));

        try {
            walletController.createPaymentIntent(amount, principal);
        } catch (ApiRequestException e) {
            // Verify that an ApiRequestException is thrown
            assertEquals("Failed to create payment intent", e.getMessage());
        }
    }

    @Test
    void testTopUpSuccess() {
        // Mock data
        WalletRequest request = new WalletRequest();
        request.setAmount(100.0);

        // ARRANGE for success scenario
        doNothing().when(walletService).addMoney(request.getEmail(), request.getAmount());

        RedirectView redirectView = walletController.topUp(request);

        // Verify redirection URL
        assertEquals(frontendEndpoint + "/wallet", redirectView.getUrl());

        // Verify service method invocation
        verify(walletService).addMoney(request.getEmail(), request.getAmount());
    }

    @Test
    void testTopUpFailure() {

        WalletRequest request = new WalletRequest();
        request.setAmount(100.0);
        request.setEmail("test@gmail.com");

        doThrow(new ApiRequestException("Failed to add money")).when(walletService).addMoney(request.getEmail(),
                request.getAmount());
        RedirectView redirectView = walletController.topUp(request);

        // ASSERT
        assertEquals(frontendEndpoint + "/payment/success", redirectView.getUrl());
    }

    @Test
    void testWalletHistory() {
        // Mock data
        Principal principal = () -> "user@example.com";
        List<Wallet> history = new ArrayList<>();

        // ARRANGE
        when(walletService.gethistory(principal)).thenReturn(history);

        ResponseEntity<List<Wallet>> responseEntity = walletController.walletHistory(principal);

        // Verify response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(history, responseEntity.getBody());
    }

}
