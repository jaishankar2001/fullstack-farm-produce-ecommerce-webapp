package com.example.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.Principal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.backend.dto.request.OrderRequest;
import com.example.backend.dto.response.OrderDto;
import com.example.backend.entities.Order;
import com.example.backend.services.OrderService;


@SpringBootTest
public class OrderTests {
    @Autowired
    OrderService orderService;

    @Test
    public void testOrderHistory(){
    //    assertFalse(orderService.orderHistory(null));
       assertTrue( orderService.orderHistory(null) instanceof List);
    }
}
