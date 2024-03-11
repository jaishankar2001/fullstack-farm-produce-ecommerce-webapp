package com.example.backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.backend.entities.Images;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.backend.dto.request.OrderRequest;
import com.example.backend.dto.response.OrderDto;
import com.example.backend.entities.Order;
import com.example.backend.entities.User;
import com.example.backend.repository.OrderRepository;
import com.example.backend.repository.UserMetaRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.OrderService;
import com.example.backend.services.impl.OrderServiceImpl;


@SpringBootTest
public class OrderTests {
    // @Autowired
    // OrderService orderService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;
    private OrderService orderService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderServiceImpl(userRepository, null, null, null, orderRepository, null);
    }
    @Test
    public void testOrderHistory(){
 
        Principal principal = mock(Principal.class);
        User user = new User();
        when(principal.getName()).thenReturn(user.getEmail());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(orderRepository.findByUser(user)).thenReturn(Collections.emptyList());
        assertTrue( orderService.orderHistory(principal) instanceof List);
        assertNotNull(orderService.orderHistory(principal));
    }
}
