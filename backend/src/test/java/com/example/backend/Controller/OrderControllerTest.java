package com.example.backend.Controller;

import com.example.backend.controller.OrderController;
import com.example.backend.dto.request.OrderRequest;
import com.example.backend.dto.response.OrderDto;
import com.example.backend.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderControllerTest {
    @Mock
    private OrderService orderServiceMock;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPlaceOrder() {
        OrderRequest orderRequest = new OrderRequest();
        Principal principal = mock(Principal.class);
        doNothing().when(orderServiceMock).placeOrder(orderRequest, principal);

        ResponseEntity<String> responseEntity = orderController.placeOrder(orderRequest, principal);

        verify(orderServiceMock).placeOrder(orderRequest, principal);

        // ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Order placed successfully", responseEntity.getBody());
    }

    @Test
    public void testOrderHistory() {
        Principal principal = mock(Principal.class);
        List<OrderDto> orderHistory = Arrays.asList(new OrderDto());

        // ARRANGE
        when(orderServiceMock.orderHistory(principal)).thenReturn(orderHistory);

        ResponseEntity<List<OrderDto>> responseEntity = orderController.orderHistory(principal);

        // Verify response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(orderHistory, responseEntity.getBody());

    }

}
