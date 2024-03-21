package com.example.backend.Controller;

import com.example.backend.controller.CustomerController;
import com.example.backend.dto.response.FarmDto;
import com.example.backend.services.FarmerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerControllerTest {

    @Mock
    private FarmerService farmerServiceMock;
    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listFarms() {
        List<FarmDto> farmList = Arrays.asList(new FarmDto(), new FarmDto());

        // ARRANGE
        when(farmerServiceMock.getAllFarms("TestFarm")).thenReturn(farmList);

        ResponseEntity<List<FarmDto>> responseEntity = customerController.listFarms("TestFarm");

        // Verify response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(farmList, responseEntity.getBody());

    }
}
