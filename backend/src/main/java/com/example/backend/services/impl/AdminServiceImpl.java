package com.example.backend.services.impl;

import com.example.backend.dto.response.*;
import com.example.backend.entities.Farms;
import com.example.backend.entities.Order;
import com.example.backend.entities.Product;
import com.example.backend.entities.User;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.FarmRepository;
import com.example.backend.repository.OrderRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.AdminService;
import com.example.backend.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final FarmRepository farmRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;


    @Override
    public AdminResponse getAllInfo(Principal principal){
        User user = userRepository.findByEmail(principal.getName());
        if(!Objects.equals(String.valueOf(user.getRole()), "ADMIN")){
            throw new ApiRequestException("Unauthorized");
        }
        AdminResponse adminResponse = new AdminResponse();
        List<Farms> allFarms = farmRepository.findAll();
        List<FarmDto> farms = allFarms.stream().map(ResponseUtils::convertFarmResponse).toList();
        adminResponse.setFarms(farms);

        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> products = allProducts.stream().map(ResponseUtils::convertProductResponse)
                .toList();
        adminResponse.setProducts(products);

        List<Order> allOrders = orderRepository.findAll();
        adminResponse.setOrders(allOrders);

        List<User> allUsers = userRepository.findAll();
        List<UserDTO> users = allUsers.stream().map(ResponseUtils::convertUserResponse)
                .toList();
        adminResponse.setUsers(users);
        adminResponse.setOrderSales(getOrderByMonth());
        return adminResponse;
    }


    public List<SalesDTO> getOrderByMonth() {

        LocalDate endDate = LocalDate.now(TimeZone.getTimeZone("AST").toZoneId());
        int year = endDate.getYear();
        int month = endDate.getMonthValue()+1;
        List<SalesDTO> salesList = new ArrayList<>();
        for (int i=0; i<4; i++){
            month -= 1;
            if(month==0){
                year -= 1;
                month = 12;
            }
            double salesValue = 0;
            LocalDate startDate = LocalDate.of(year, month, 1);
            SalesDTO sale = new SalesDTO();
            sale.setMonth(Month.of(month).name());
            List<Order> orders = orderRepository.findByOrderDateBetween(startDate.atStartOfDay(), endDate.atStartOfDay());
            endDate = startDate;
            for (Order order : orders) {
                salesValue += order.getOrderValue();
            }
            sale.setSales(salesValue);
            salesList.add(sale);
        }
        return salesList;
    }
}
