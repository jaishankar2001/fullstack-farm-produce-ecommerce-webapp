package com.example.backend.services.impl;

import org.springframework.stereotype.Service;

import java.security.Principal;
import com.example.backend.dto.request.OrderRequest;
import com.example.backend.entities.Product;
import com.example.backend.entities.User;
import com.example.backend.entities.UserMeta;
import com.example.backend.exception.ApiException;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.UserMetaRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final UserMetaRepository userMetaRepository;


    @Override
    public void makeOrder(OrderRequest orderRequest, Principal principal) {
        System.out.println(principal.getName());
        User user = userRepository.findByEmail(principal.getName());
        UserMeta userDetails = userMetaRepository.findByUser(user);


        // Product product = 
        // // TODO : - change afterwords , currently multiplying it with 5
        // if(orderRequest.getOrderValue() * 5 < userDetails.getWallet_balance()) {
        //     System.out.println("Can place order");
        // } else {
        //     throw new ApiRequestException("Cannot add order as you do not have enough balance");
        // }
        
        
    }
    
}
