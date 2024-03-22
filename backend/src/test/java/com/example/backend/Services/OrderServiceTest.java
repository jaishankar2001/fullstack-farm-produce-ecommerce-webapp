package com.example.backend.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.security.Principal;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.backend.dto.request.OrderRequest;
import com.example.backend.entities.*;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.backend.dto.response.OrderDto;
import com.example.backend.services.impl.OrderServiceImpl;

public class OrderServiceTest {
        @Mock
        private UserRepository userRepository;

        @Mock
        private UserMetaRepository userMetaRepository;

        @Mock
        private OrderRepository orderRepository;

        @Mock
        private ProductRepository productRepository;

        @Mock
        private FarmRepository farmRepository;

        @Mock
        private OrderServiceImpl orderService;

        @Mock
        private WalletRepository walletRepository;

        private User mockUser;
        private List<Order> mockOrders;
        private Principal principal;

        static final double ORDER_VALUE = 100.0;
        static final int ORDER_QUANTITY = 2;

//        @BeforeEach
//        public void setUp() {
//                MockitoAnnotations.openMocks(this);
//        }

        @BeforeEach
        void setUp() {
                int orderId = 1;
                int productid = 1;
                MockitoAnnotations.openMocks(this);

                orderService = new OrderServiceImpl(userRepository, userMetaRepository, productRepository, farmRepository, orderRepository, walletRepository);
                mockUser = new User();
                mockUser.setEmail("test@example.com");

                mockOrders = new ArrayList<>();
                Order order1 = new Order();
                order1.setId(orderId);

                Images image1 = new Images();
                ArrayList<Images> imgArr = new ArrayList<>();
                imgArr.add(image1);
                Product product = new Product();
                product.setId(productid);
                product.setProductName("Test Product");
                product.setProductDescription("Test Product Description");
                product.setImages(imgArr);

                Farms farm = new Farms();
                farm.setId(1);
                farm.setName("Test Farm");

                order1.setProduct(product);
                order1.setFarm(farm);
                order1.setOrderDate(LocalDateTime.now());
                order1.setOrderValue(ORDER_VALUE);
                order1.setQuantity(ORDER_QUANTITY);

                mockOrders.add(order1);
                principal = mock(Principal.class);

        }

        @Test
        public void testOrderHistory() {
                int orderId = 1;
                when(userRepository.findByEmail(anyString())).thenReturn(mockUser);
                when(orderRepository.findByUser(any(User.class))).thenReturn(mockOrders);

                Principal mockPrincipal = () -> "test@example.com";
                List<OrderDto> orderDtoList = orderService.orderHistory(mockPrincipal);

                assertEquals(mockOrders.size(), orderDtoList.size());
                OrderDto orderDto = orderDtoList.get(0);
                assertTrue(orderService.orderHistory(mockPrincipal) instanceof List);
                assertEquals(Long.valueOf(orderId), orderDto.getId());
                assertEquals("Test Product", orderDto.getProductName());
                assertEquals("Test Product Description", orderDto.getProductDescription());
                assertEquals("Test Farm", orderDto.getFarmName());
                assertEquals(Double.valueOf(ORDER_VALUE), orderDto.getOrderValue());
                assertEquals(Integer.valueOf(ORDER_QUANTITY), orderDto.getQuantity());
        }

        @Test
        public void testWhenUSerNotPresent() {
                when(userRepository.findByEmail(anyString())).thenReturn(null);
                when(orderRepository.findByUser(any(User.class))).thenReturn(new ArrayList<>());
                Principal mockNullPrincipal = mock(Principal.class);
                when(mockNullPrincipal.getName()).thenReturn(null);
                assertThrows(ApiRequestException.class, () -> orderService.orderHistory(mockNullPrincipal),
                                "User not Found");

        }

        @Test
        public void testPlaceOrderSuccess(){
                OrderRequest orderRequest = new OrderRequest();
                orderRequest.setProduct_id(1);
                orderRequest.setFarm_id(1);
                orderRequest.setQuantity(1);

                UserMeta userMeta = new UserMeta();
                userMeta.setWallet_balance(200);
                User user = new User();
                user.setUserMeta(userMeta);

                Product product = new Product();
                product.setStock(1);
                product.setPrice(100.0);

                Farms farms = new Farms();
                String email = "test@gmail.com";
                when(principal.getName()).thenReturn(email);
                when(userRepository.findByEmail(email)).thenReturn(user);
                when(userMetaRepository.findByUser(user)).thenReturn(userMeta);
                when(productRepository.findById(anyInt())).thenReturn(product);
                when(farmRepository.findById(orderRequest.getFarm_id())).thenReturn(farms);

                orderService.placeOrder(orderRequest, principal);

                verify(orderRepository, times(1)).save(any(Order.class));
                verify(userMetaRepository, times(1)).save(any(UserMeta.class));
                verify(productRepository, times(1)).save(any(Product.class));
                assertEquals(0, product.getStock());
        }

        @Test
        public void testProductNull() {
                OrderRequest orderRequest = new OrderRequest();

                when(productRepository.findById(anyInt())).thenReturn(null);

                //ASSERT
                Throwable thrown = assertThrows(
                        ApiRequestException.class,
                        () -> orderService.placeOrder(orderRequest, principal));

                assertEquals("Product not found", thrown.getMessage());
        }

        @Test
        public void testFarmNull() {
                OrderRequest orderRequest = new OrderRequest();
                Product product = new Product();

                when(productRepository.findById(anyInt())).thenReturn(product);
                when(farmRepository.findById(anyInt())).thenReturn(null);

                //ASSERT
                Throwable thrown = assertThrows(
                        ApiRequestException.class,
                        () -> orderService.placeOrder(orderRequest, principal));

                assertEquals("Farm not found", thrown.getMessage());
        }

        @Test
        public void testOutOfStock() {
                OrderRequest orderRequest = new OrderRequest();
                orderRequest.setProduct_id(1);
                orderRequest.setFarm_id(1);
                orderRequest.setQuantity(2);

                Product product = new Product();
                product.setStock(1);

                Farms farms = new Farms();

                UserMeta userMeta = new UserMeta();

                when(productRepository.findById(anyInt())).thenReturn(product);
                when(farmRepository.findById(anyInt())).thenReturn(farms);

                //ASSERT
                Throwable thrown = assertThrows(
                        ApiRequestException.class,
                        () -> orderService.placeOrder(orderRequest, principal));

                assertEquals("Order cannot have more quantity than total stock", thrown.getMessage());
        }

        @Test
        public void TestInsufficientBalance() {
                OrderRequest orderRequest = new OrderRequest();
                orderRequest.setProduct_id(1);
                orderRequest.setFarm_id(1);
                orderRequest.setQuantity(1);

                UserMeta userMeta = new UserMeta();
                userMeta.setWallet_balance(10);
                User user = new User();
                user.setUserMeta(userMeta);

                Product product = new Product();
                product.setStock(1);
                product.setPrice(100.0);

                Farms farms = new Farms();
                String email = "test@gmail.com";
                when(principal.getName()).thenReturn(email);
                when(userRepository.findByEmail(email)).thenReturn(user);
                when(userMetaRepository.findByUser(user)).thenReturn(userMeta);
                when(productRepository.findById(anyInt())).thenReturn(product);
                when(farmRepository.findById(orderRequest.getFarm_id())).thenReturn(farms);

                //ASSERT
                Throwable thrown = assertThrows(
                        ApiRequestException.class,
                        () -> orderService.placeOrder(orderRequest, principal));

                assertEquals("You do not have sufficient balance to buy this product", thrown.getMessage());
        }
}
