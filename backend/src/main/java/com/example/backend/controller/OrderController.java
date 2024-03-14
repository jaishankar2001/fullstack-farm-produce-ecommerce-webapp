package com.example.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import com.example.backend.dto.request.OrderRequest;
import com.example.backend.dto.response.OrderDto;
import com.example.backend.services.OrderService;
import java.util.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/place-order")
    public ResponseEntity<String> placeOrder(@RequestBody @Valid OrderRequest orderRequest, Principal principal) {
        orderService.placeOrder(orderRequest, principal);
        return ResponseEntity.ok("Order placed successfully");
    }

    @GetMapping("/orderHistory")
    public ResponseEntity<List<OrderDto>> orderHistory(Principal principal) {
        List<OrderDto>orderHistory = orderService.orderHistory(principal);
        return ResponseEntity.ok(orderHistory);
    }
    
}
