package com.example.backend.Services;

import com.example.backend.dto.response.AdminResponse;
import com.example.backend.dto.response.SalesDTO;
import com.example.backend.entities.Order;
import com.example.backend.entities.Role;
import com.example.backend.entities.User;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.FarmRepository;
import com.example.backend.repository.OrderRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.impl.AdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    void testGetAllInfo() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("admin@example.com");


        User adminUser = new User();
        adminUser.setEmail("admin@example.com");
        adminUser.setRole(Role.valueOf("ADMIN"));

        when(userRepository.findByEmail("admin@example.com")).thenReturn(adminUser);
        when(farmRepository.findAll()).thenReturn(new ArrayList<>());
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        when(userRepository.findAll()).thenReturn(new ArrayList<>());


        AdminResponse adminResponse = adminService.getAllInfo(principal);


        assertNotNull(adminResponse);
        assertTrue(adminResponse.getFarms().isEmpty());
        assertTrue(adminResponse.getProducts().isEmpty());
        assertTrue(adminResponse.getOrders().isEmpty());
        assertTrue(adminResponse.getUsers().isEmpty());

    }

    @Test
    void testGetAllInfo_Unauthorized() {

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("user@example.com");


        User normalUser = new User();
        normalUser.setEmail("user@example.com");
        normalUser.setRole(Role.valueOf("CUSTOMER"));


        when(userRepository.findByEmail("user@example.com")).thenReturn(normalUser);


        assertThrows(ApiRequestException.class, () -> adminService.getAllInfo(principal));

        verifyNoInteractions(farmRepository, productRepository, orderRepository);
    }
    @Test
    void testGetOrderByMonth() {

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setOrderValue(100);
        orders.add(order1);
        Order order2 = new Order();
        order2.setOrderValue(200);
        orders.add(order2);
        when(orderRepository.findByOrderDateBetween(any(), any())).thenReturn(orders);


        List<SalesDTO> salesList = adminService.getOrderByMonth();


        assertNotNull(salesList);
        assertEquals(4, salesList.size());
        assertEquals("MARCH", salesList.get(0).getMonth());
        assertEquals(300, salesList.get(0).getSales());

    }


}


