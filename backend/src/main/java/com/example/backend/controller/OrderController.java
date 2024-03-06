package com.example.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import com.example.backend.dto.request.OrderRequest;
import com.example.backend.services.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
}
