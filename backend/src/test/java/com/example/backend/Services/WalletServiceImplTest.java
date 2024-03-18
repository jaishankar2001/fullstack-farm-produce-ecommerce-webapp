package com.example.backend.Services;

import com.example.backend.entities.User;
import com.example.backend.entities.UserMeta;
import com.example.backend.entities.Wallet;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.UserMetaRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.WalletRepository;
import com.example.backend.services.impl.WalletServiceImpl;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class WalletServiceImplTest {

    @Value("${stripe.apikey}")
    private String stripeAPI;

    @BeforeEach
    public void setUp() {
        Stripe.apiKey = stripeAPI;
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private UserMetaRepository userMetaRepositoryMock;

    @Mock
    private WalletRepository walletRepositoryMock;

    @InjectMocks
    private WalletServiceImpl walletService;

    @Test
    public void testUpdateBalance_Success() {
        String email = "test@example.com";
        double initialBalance = 100.0;
        double amountToAdd = 50.0;

        UserMeta userMeta = new UserMeta();
        userMeta.setWallet_balance(initialBalance);
        User user = new User();
        user.setEmail(email);
        user.setUserMeta(userMeta);
        when(userRepositoryMock.findByEmail(email)).thenReturn(user);
        when(userMetaRepositoryMock.save(any(UserMeta.class))).thenAnswer(invocation -> invocation.getArguments()[0]);
        when(walletRepositoryMock.save(any(Wallet.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        // Act
//        UserService userService = new UserService(userRepository, userMetaRepository, walletRepository);
        walletService.addMoney(email, amountToAdd);

        // Assert
        assertEquals(initialBalance + amountToAdd, userMeta.getWallet_balance()); // Assuming a delta of 0.01 for double comparison
        verify(userRepositoryMock, times(1)).findByEmail(email);
        verify(userMetaRepositoryMock, times(1)).save(any(UserMeta.class));
        verify(walletRepositoryMock, times(1)).save(any(Wallet.class));
    }

    @Test
    public void testUpdateBalance_UserNotFound() {
        // Arrange
        String email = "nonexistent@example.com";
        User user = new User();
        user.setEmail(email);
        user.setUserMeta(null);

        when(userRepositoryMock.findByEmail(email)).thenReturn(user);

        // Act
        walletService.addMoney(email, 50.0);

        // Assert
        verify(userRepositoryMock, times(1)).findByEmail(email);
        verify(userMetaRepositoryMock, never()).save(any(UserMeta.class));
        verify(walletRepositoryMock, never()).save(any(Wallet.class));
    }

    @Test
    public void testWalletHistorySuccess() {
        String email = "test@example.com";
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn(email);

        User user = new User();
        user.setId(1);

        List<Wallet> expectedWallets = Arrays.asList(new Wallet(), new Wallet());

        // Mock repository behavior
        when(userRepositoryMock.findByEmail(email)).thenReturn(user);
        when(walletRepositoryMock.findAllByUserId(user.getId(), Sort.by(Sort.Direction.DESC, "createdAt"))).thenReturn(expectedWallets);

        List<Wallet> actualWallets = walletService.gethistory(principal);

        // Verify results
        assertEquals(expectedWallets, actualWallets);

    }

    @Test
    public void testWalletHistoryFailure() {
        String email = "test@example.com";
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn(email);
        when(userRepositoryMock.findByEmail(email)).thenReturn(null);

        assertThrows(ApiRequestException.class, () -> walletService.gethistory(principal));
    }

}
