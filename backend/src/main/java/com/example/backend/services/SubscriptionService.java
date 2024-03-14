package com.example.backend.services;

import java.security.Principal;

import com.example.backend.dto.request.ProductSubscribeRequest;

public interface SubscriptionService {
    void subscribeProduct(ProductSubscribeRequest request, Principal principal);

    void runCron();
}
