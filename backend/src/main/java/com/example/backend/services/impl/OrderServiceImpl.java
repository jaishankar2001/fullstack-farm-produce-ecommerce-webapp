package com.example.backend.services.impl;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;

import com.example.backend.dto.request.OrderRequest;
import com.example.backend.entities.Farms;
import com.example.backend.entities.Order;
import com.example.backend.entities.Product;
import com.example.backend.entities.User;
import com.example.backend.entities.UserMeta;
import com.example.backend.exception.ApiException;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.FarmRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserMetaRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

private final UserRepository userRepository;
private final UserMetaRepository userMetaRepository;
private final ProductRepository productRepository;
private final FarmRepository farmRepository;


@Override
public void makeOrder(OrderRequest orderRequest, Principal principal) {
System.out.println(principal.getName());
User user = userRepository.findByEmail(principal.getName());
UserMeta userDetails = userMetaRepository.findByUser(user);
Product product = productRepository.findById(orderRequest.getProduct_id());
Farms farm = farmRepository.findById(orderRequest.getFarm_id());
if(product != null) {
throw new ApiRequestException("Product not found");
}

if(farm != null) {
throw new ApiRequestException("Farm not found");
}

// Order order = new Order();
// order.setUser(user);
// order.setProduct(product);
// order.setFarm(farm);
// order.setOrderDate(LocalDateTime.now());

BigDecimal product_price = product.getPrice();
BigDecimal total_price = product_price.multiply(orderRequest.getQuantity());
System.out.println("HEREEE?" + product_price + orderRequest.getQuantity() + total_price);




// Product product =
// // TODO : - change afterwords , currently multiplying it with 5
// if(orderRequest.getOrderValue() * 5 < userDetails.getWallet_balance()) {
// System.out.println("Can place order");
// } else {
// throw new ApiRequestException("Cannot add order as you do not have enough balance");
// }


}

}