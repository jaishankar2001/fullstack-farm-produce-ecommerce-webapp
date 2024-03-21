package com.example.backend.Controller;

import com.example.backend.controller.SubscriptionController;
import com.example.backend.dto.request.ProductSubscribeRequest;
import com.example.backend.dto.response.GetSubscriptionResponse;
import com.example.backend.services.SubscriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SubscriptionControllerTest {
    @Mock
    private SubscriptionService subscriptionServiceMock;

    @InjectMocks
    private SubscriptionController  subscriptionController;

    private Principal principal;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        principal = mock(Principal.class);
    }

    @Test
    public void testSubscribeProduct() {
        // ARRANGE
        ProductSubscribeRequest request = new ProductSubscribeRequest();

        doNothing().when(subscriptionServiceMock).subscribeProduct(request, principal);

        ResponseEntity<String> responseEntity = subscriptionController.subscribeProduct(request, principal);

        verify(subscriptionServiceMock).subscribeProduct(request, principal);

        // ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Subscribed to product successfully!", responseEntity.getBody());
    }

    @Test
    public void testGetOwnSubscription() {
        // ARRANGE
        Principal principal = () -> "user@example.com";
        List<GetSubscriptionResponse> response = new ArrayList<>();

        // ACT
        when(subscriptionServiceMock.getOwnSubscription(principal)).thenReturn(response);

        ResponseEntity<List<GetSubscriptionResponse>> responseEntity = subscriptionController.getOwnSubscription(principal);

        // ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(response, responseEntity.getBody());
    }

    @Test
    public void testMySubscribedProduct() {
        List<GetSubscriptionResponse> response = new ArrayList<>();

        // ACT
        when(subscriptionServiceMock.getMySubscribedProduct(principal)).thenReturn(response);

        ResponseEntity<List<GetSubscriptionResponse>> responseEntity = subscriptionController.getMySubscribedProduct(principal);

        // ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(response, responseEntity.getBody());

    }

    @Test
    void testRunCron() {
        // ARRANGE
        ResponseEntity<String> responseEntity = subscriptionController.runCron();

        // ACt
        verify(subscriptionServiceMock).runCron();

        // ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("cronjob run successfully!", responseEntity.getBody());
    }
}
