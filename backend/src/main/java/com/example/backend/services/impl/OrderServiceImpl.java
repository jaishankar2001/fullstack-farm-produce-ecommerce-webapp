package com.example.backend.services.impl;

import org.springframework.stereotype.Service;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.backend.dto.request.OrderRequest;
import com.example.backend.dto.response.OrderDto;
import com.example.backend.entities.Farms;
import com.example.backend.entities.Order;
import com.example.backend.entities.Product;
import com.example.backend.entities.User;
import com.example.backend.entities.UserMeta;
import com.example.backend.entities.Wallet;
import com.example.backend.exception.ApiException;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.FarmRepository;
import com.example.backend.repository.OrderRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserMetaRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.WalletRepository;
import com.example.backend.services.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final UserMetaRepository userMetaRepository;
    private final ProductRepository productRepository;
    private final FarmRepository farmRepository;
    private final OrderRepository orderRepository;
    private final WalletRepository walletRepository;

    @Override
    public void placeOrder(OrderRequest orderRequest, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        UserMeta userDetails = userMetaRepository.findByUser(user);
        Product product = productRepository.findById(orderRequest.getProduct_id());
        Farms farm = farmRepository.findById(orderRequest.getFarm_id());
        if (product == null) {
            throw new ApiRequestException("Product not found");
        }

        if (farm == null) {
            throw new ApiRequestException("Farm not found");
        }

        double product_price = product.getPrice();
        double total_price = product_price * orderRequest.getQuantity();

        // check if customer has valid amount of
        if (userDetails.getWallet_balance() < total_price) {
            throw new ApiRequestException("You do not have sufficient balance to buy this product");
        }
        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setFarm(farm);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderValue(total_price);
        order.setOrderPaymentMethod("Wallet");
        orderRepository.save(order);

        Wallet wallet = new Wallet();
        wallet.setAmount(total_price);
        wallet.setUser(user);
        wallet.setPaymnent_Method_Reference("Order");
        walletRepository.save(wallet);

        userDetails.setWallet_balance(userDetails.getWallet_balance() - total_price);
        userMetaRepository.save(userDetails);

    }

    @Override
    public List<OrderDto> orderHistory(Principal principal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orderHistory'");
    }

}