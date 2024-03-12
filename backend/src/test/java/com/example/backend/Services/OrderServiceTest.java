package com.example.backend.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.backend.entities.*;
import com.example.backend.exception.ApiRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.backend.dto.response.OrderDto;
import com.example.backend.repository.OrderRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.impl.OrderServiceImpl;

public class OrderServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderServiceImpl orderService;

    private User mockUser;
    private List<Order> mockOrders;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        orderService = new OrderServiceImpl(userRepository, null, null, null, orderRepository, null);
        mockUser = new User();
        mockUser.setEmail("test@example.com");

        mockOrders = new ArrayList<>();
        Order order1 = new Order();
        order1.setId(1);

        Images image1 = new Images();
        ArrayList<Images> imgArr = new ArrayList<>();
        imgArr.add(image1);
        Product product = new Product();
        product.setId(1);
        product.setProductName("Test Product");
        product.setProductDescription("Test Product Description");
        product.setImages(imgArr);

        Farms farm = new Farms();
        farm.setId(1);
        farm.setName("Test Farm");

        order1.setProduct(product);
        order1.setFarm(farm);
        order1.setOrderDate(LocalDateTime.now());
        order1.setOrderValue(100.0);
        order1.setQuantity(2);

        mockOrders.add(order1);

    }

    @Test
    public void testOrderHistory() {

        when(userRepository.findByEmail(anyString())).thenReturn(mockUser);
        when(orderRepository.findByUser(any(User.class))).thenReturn(mockOrders);

        Principal mockPrincipal = () -> "test@example.com";
        List<OrderDto> orderDtoList = orderService.orderHistory(mockPrincipal);

        assertEquals(mockOrders.size(), orderDtoList.size());
        OrderDto orderDto = orderDtoList.get(0);
        assertTrue(orderService.orderHistory(mockPrincipal) instanceof List);
        assertEquals(Long.valueOf(1), orderDto.getId());
        assertEquals("Test Product", orderDto.getProductName());
        assertEquals("Test Product Description", orderDto.getProductDescription());
        assertEquals("Test Farm", orderDto.getFarmName());
        assertEquals(Double.valueOf(100.0), orderDto.getOrderValue());
        assertEquals(Integer.valueOf(2), orderDto.getQuantity());
    }

    @Test
    public void testWhenUSerNotPresent() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(orderRepository.findByUser(any(User.class))).thenReturn(new ArrayList<>());
        Principal mockNullPrincipal = mock(Principal.class);
        when(mockNullPrincipal.getName()).thenReturn(null);
        assertThrows(ApiRequestException.class, () -> orderService.orderHistory(mockNullPrincipal), "User not Found");

    }
}
