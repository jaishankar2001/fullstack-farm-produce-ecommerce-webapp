package com.example.backend.java.Services;

import com.example.backend.dto.request.ProductSubscribeRequest;
import com.example.backend.entities.Farms;
import com.example.backend.entities.Product;
import com.example.backend.entities.Subscription;
import com.example.backend.entities.User;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.FarmRepository;
import com.example.backend.repository.ProductRepository;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.backend.repository.SubscriptionRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.SubscriptionService;
import com.example.backend.services.impl.SubscriptionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceImplTest {

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private FarmRepository farmRepositoryMock;

    @Mock
    private SubscriptionRepository subscriptionRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private SubscriptionServiceImpl subscriptionServiceImpl;

    @Test
    public void testProductNull() {
        ProductSubscribeRequest request = new ProductSubscribeRequest();
        request.setProduct_id(100);
        when(productRepositoryMock.findById(100)).thenReturn(null);
        assertThrows(ApiRequestException.class,
                () -> subscriptionServiceImpl.subscribeProduct(request, mock(Principal.class)));
    }

    @Test
    public void testFarmNull() {
        ProductSubscribeRequest request = new ProductSubscribeRequest();
        Product product = new Product();
        when(productRepositoryMock.findById(anyInt())).thenReturn(product);
        when(farmRepositoryMock.findById(anyInt())).thenReturn(null);
        assertThrows(ApiRequestException.class,
                () -> subscriptionServiceImpl.subscribeProduct(request, mock(Principal.class)));
    }

    @Test
    public void testUserAlreadySubscribed() {
        ProductSubscribeRequest request = new ProductSubscribeRequest();
        when(productRepositoryMock.findById(anyInt())).thenReturn(new Product());
        when(farmRepositoryMock.findById(anyInt())).thenReturn(new Farms());
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("abc@gmail.com");
        when(userRepositoryMock.findByEmail(anyString())).thenReturn(new User());
        List<Subscription> subscriptions = new ArrayList<>();
        Subscription subscription = new Subscription();
        subscriptions.add(subscription);
        when(subscriptionRepositoryMock.findAllByUserIdAndProductId(anyInt(), anyInt())).thenReturn(subscriptions);
        assertThrows(ApiRequestException.class, () -> subscriptionServiceImpl.subscribeProduct(request, principal));
    }
}
