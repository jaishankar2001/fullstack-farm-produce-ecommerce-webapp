package com.example.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.dto.request.ProductSubscribeRequest;
import com.example.backend.dto.response.GetSubscriptionResponse;
import com.example.backend.services.SubscriptionService;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@RestController
@RequestMapping("/api/subscribe")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/product")
    public ResponseEntity<String> subscribeProduct(@RequestBody ProductSubscribeRequest request, Principal principal) {
        subscriptionService.subscribeProduct(request, principal);
        return ResponseEntity.ok("Subscribed to product successfully!");
    }

    @GetMapping("/run-cron")
    public ResponseEntity<String> getMethodName() {
        subscriptionService.runCron();
        return ResponseEntity.ok("cronjob run successfully!");
    }

    @GetMapping("/my-subscription")
    public ResponseEntity<List<GetSubscriptionResponse>> getOwnSubscription(Principal principal) {
        List<GetSubscriptionResponse> response = subscriptionService.getOwnSubscription(principal);
        return ResponseEntity.ok(response);
    }

}
