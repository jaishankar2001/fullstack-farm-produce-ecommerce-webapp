package com.example.backend.Services;

import com.example.backend.dto.request.ProductSubscribeRequest;
import com.example.backend.entities.*;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.example.backend.services.SubscriptionService;
import com.example.backend.services.impl.SubscriptionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionServiceImplTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private FarmRepository farmRepositoryMock;

    @Mock
    private OrderRepository orderRepositoryMock;

    @Mock
    private SubscriptionRepository subscriptionRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private UserMetaRepository userMetaRepositoryMock;

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

    @Test
    public void testUserDoesNotHaveEnoughBalance() {
        ProductSubscribeRequest request = new ProductSubscribeRequest();
        Product product = new Product();
        product.setPrice(100);
        when(productRepositoryMock.findById(anyInt())).thenReturn(product);
        when(farmRepositoryMock.findById(anyInt())).thenReturn(new Farms());
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("abc@gmail.com");
        when(userRepositoryMock.findByEmail(anyString())).thenReturn(new User());
        List<Subscription> subscriptions = new ArrayList<>();
        when(subscriptionRepositoryMock.findAllByUserIdAndProductId(anyInt(), anyInt())).thenReturn(subscriptions);
        UserMeta userMeta = new UserMeta();
        userMeta.setWallet_balance(0);
        when(userMetaRepositoryMock.findByUser(any())).thenReturn(userMeta);
        assertThrows(ApiRequestException.class, () -> subscriptionServiceImpl.subscribeProduct(request, principal));
    }

    @Test
    public void testProductSubscription() {
        ProductSubscribeRequest request = new ProductSubscribeRequest();
        request.setMon(1);
        request.setThu(1);
        request.setTue(1);
        request.setWed(1);
        request.setFri(1);
        request.setSat(1);
        request.setSun(1);
        Product product = new Product();
        product.setPrice(100);

        when(productRepositoryMock.findById(anyInt())).thenReturn(product);
        when(farmRepositoryMock.findById(anyInt())).thenReturn(new Farms());
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("abc@gmail.com");
        when(userRepositoryMock.findByEmail(anyString())).thenReturn(new User());
        List<Subscription> subscriptions = new ArrayList<>();
        when(subscriptionRepositoryMock.findAllByUserIdAndProductId(anyInt(), anyInt())).thenReturn(subscriptions);

        UserMeta userMeta = new UserMeta();
        userMeta.setWallet_balance(product.getPrice() + 100);
        when(userMetaRepositoryMock.findByUser(any())).thenReturn(userMeta);
        subscriptionServiceImpl.subscribeProduct(request, principal);
        verify(subscriptionRepositoryMock, times(7)).save(any(Subscription.class));
    }

    @Test
    public void testCronForMakeOrder() {
        Product product = new Product();
        product.setPrice(100);
        List<Subscription> subscriptions = new ArrayList<>();
        Subscription subscription = new Subscription();
        subscription.setProduct(product);
        subscriptions.add(subscription);
        when(subscriptionRepositoryMock.findByDays(any())).thenReturn(subscriptions);
        subscriptionServiceImpl.CronForMakeOrder();
        Mockito.verify(orderRepositoryMock,
                Mockito.times(subscriptions.size())).save(Mockito.any(Order.class));
    }

}
