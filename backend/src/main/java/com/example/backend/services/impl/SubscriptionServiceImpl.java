package com.example.backend.services.impl;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.backend.dto.request.ProductSubscribeRequest;
import com.example.backend.entities.Days;
import com.example.backend.entities.Farms;
import com.example.backend.entities.Order;
import com.example.backend.entities.OrderType;
import com.example.backend.entities.Product;
import com.example.backend.entities.Subscription;
import com.example.backend.entities.User;
import com.example.backend.entities.UserMeta;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.FarmRepository;
import com.example.backend.repository.OrderRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.SubscriptionRepository;
import com.example.backend.repository.UserMetaRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.SubscriptionService;
import java.time.LocalDate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final UserRepository userRepository;
    private final UserMetaRepository userMetaRepository;
    private final ProductRepository productRepository;
    private final FarmRepository farmRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final OrderRepository orderRepository;

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
            subscribeDays.add(Days.MONDAY.name());
        }
        if (request.getTue() == 1) {
            subscribeDays.add(Days.TUESDAY.name());
        }
        if (request.getWed() == 1) {
            subscribeDays.add(Days.WEDNESDAY.name());
        }
        if (request.getThu() == 1) {
            subscribeDays.add(Days.THURSDAY.name());
        }
        if (request.getFri() == 1) {
            subscribeDays.add(Days.FRIDAY.name());
        }
        if (request.getSat() == 1) {
            subscribeDays.add(Days.SATURDAY.name());
        }
        if (request.getSun() == 1) {
            subscribeDays.add(Days.SUNDAY.name());
        }

        for (String day : subscribeDays) {
            Subscription subscription = new Subscription();
            Days enumDay = Days.valueOf(day);
            subscription.setDays(enumDay);
            subscription.setName(request.getName());
            subscription.setSubscriptionDate(LocalDateTime.now());
            subscription.setUser(user);
            subscription.setProduct(product);
            subscription.setFarm(farm);
            subscriptionRepository.save(subscription);
        }
    }

    public void runCron() {
        CronForMakeOrder();
    }

    // @Scheduled(cron = "0 * * * * *") // running every minute
    // @Scheduled(cron = "* * * * * *") // every second
    @Scheduled(cron = "55 23 * * * *") // Runs everyday at 11:55 PM
    public void CronForMakeOrder() {
        DayOfWeek currentDayOfWeek = LocalDate.now().getDayOfWeek();

        // next Day
        DayOfWeek upcomingDay = currentDayOfWeek.plus(1);
        List<Subscription> upcomingSubscriptions = subscriptionRepository
                .findByDays(Days.valueOf(upcomingDay.toString()));

        // creating order for all upcoming subscription
        for (Subscription subscription : upcomingSubscriptions) {
            User user = subscription.getUser();
            UserMeta userMeta = user.getUserMeta();
            System.out.println(userMeta.getWallet_balance());
            Product product = subscription.getProduct();
            if (userMeta.getWallet_balance() < product.getPrice()) {
                System.out.println("Can not make order, user does not have balance");
                return;
            } else {
                Order order = new Order();
                order.setUser(subscription.getUser());
                order.setProduct(subscription.getProduct());
                order.setFarm(subscription.getFarm());
                order.setOrderDate(LocalDateTime.now());
                order.setOrderValue(product.getPrice());
                order.setQuantity(1);
                order.setOrderPaymentMethod("Wallet");
                order.setOrderType(OrderType.SUBSCRIPTION);
                order.setSubscription(subscription);
                orderRepository.save(order);
                userMeta.setWallet_balance(userMeta.getWallet_balance() - product.getPrice());
                userMetaRepository.save(userMeta);
            }

        }
    }

}
