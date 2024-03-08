package com.example.backend.services.impl;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.stereotype.Service;
import com.example.backend.dto.request.ProductSubscribeRequest;
import com.example.backend.entities.Days;
import com.example.backend.entities.Farms;
import com.example.backend.entities.Product;
import com.example.backend.entities.Subscription;
import com.example.backend.entities.User;
import com.example.backend.entities.UserMeta;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.FarmRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.SubscriptionRepository;
import com.example.backend.repository.UserMetaRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.SubscriptionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final UserRepository userRepository;
    private final UserMetaRepository userMetaRepository;
    private final ProductRepository productRepository;
    private final FarmRepository farmRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public void subscribeProduct(ProductSubscribeRequest request, Principal principal) {

        Product product = productRepository.findById(request.getProduct_id());
        if (product == null) {
            throw new ApiRequestException("Product not found");
        }

        Farms farm = farmRepository.findById(request.getFarm_id());

        if (farm == null) {
            throw new ApiRequestException("Farm not found");
        }
        System.out.println("HERE?");
        User user = userRepository.findByEmail(principal.getName());
        List<Subscription> s = subscriptionRepository.findAllByUserIdAndProductId(user.getId(),
                product.getId());

        if (s.size() > 0) {
            throw new ApiRequestException("User has already subscribed to this product");
        }

        UserMeta userDetails = userMetaRepository.findByUser(user);

        // check if customer has valid amount of balance to subscribe the product
        if (userDetails.getWallet_balance() < product.getPrice()) {
            throw new ApiRequestException("You do not have sufficient balance to buy this product");
        }

        List<String> subscribeDays = new ArrayList<>();

        if (request.getMon() == 1) {
            subscribeDays.add(Days.MON.name());
        }
        if (request.getTue() == 1) {
            subscribeDays.add(Days.TUE.name());
        }
        if (request.getWed() == 1) {
            subscribeDays.add(Days.WED.name());
        }
        if (request.getThu() == 1) {
            subscribeDays.add(Days.THU.name());
        }
        if (request.getFri() == 1) {
            subscribeDays.add(Days.FRI.name());
        }
        if (request.getSat() == 1) {
            subscribeDays.add(Days.SAT.name());
        }
        if (request.getSun() == 1) {
            subscribeDays.add(Days.SUN.name());
        }

        for (String day : subscribeDays) {
            Subscription subscription = new Subscription();
            Days enumDay = Days.valueOf(day);
            subscription.setDays(enumDay);
            subscription.setName(request.getName());
            subscription.setSubscriptionDate(LocalDateTime.now());
            subscription.setUser(user);
            subscription.setProduct(product);
            subscriptionRepository.save(subscription);
        }
    }
}
