package com.example.backend.Controller;

import com.example.backend.controller.HomeController;
import com.example.backend.dto.response.HomeResponse;
import com.example.backend.services.HomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HomeControllerTest {

    @Mock
    private HomeService homeServiceMock;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetHomeMeta() {
        HomeResponse homeResponse = new HomeResponse(/* Construct home response */);

        // ARRANGE
        when(homeServiceMock.getHomeMeta()).thenReturn(homeResponse);

        ResponseEntity<HomeResponse> responseEntity = homeController.getHomeMeta();

        // Verify response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(homeResponse, responseEntity.getBody());

    }
}
