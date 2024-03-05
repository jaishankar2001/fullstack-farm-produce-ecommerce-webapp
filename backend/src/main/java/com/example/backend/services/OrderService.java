package com.example.backend.services;

import java.security.Principal;
import com.example.backend.dto.request.OrderRequest;

public interface OrderService {
    void makeOrder(OrderRequest orderRequest, Principal principal);
}
