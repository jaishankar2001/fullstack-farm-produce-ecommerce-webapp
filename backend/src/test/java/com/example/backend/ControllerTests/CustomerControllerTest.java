package com.example.backend.ControllerTests;

import com.example.backend.controller.CustomerController;
import com.example.backend.dto.response.FarmDto;
import com.example.backend.entities.Farms;
import com.example.backend.services.FarmerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


public class CustomerControllerTest {
    @Mock
    ResponseEntity responseEntity;

    @InjectMocks
    CustomerController customerController;
    @Mock
    FarmerService farmerService;
    @Mock
    Farms farm;
    @Mock
    FarmDto farmDto;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        when(farm.getName()).thenReturn("testFarm");
    }

    @Test
    public void  testListFarms(){
        assertTrue(customerController.listFarms(farm.getName()).getStatusCode().is2xxSuccessful());
    }

    @Test
    public void testListFarmsBody(){
        List<FarmDto> farmList = farmerService.getAllFarms(farm.getName());
        responseEntity = customerController.listFarms(farm.getName());
        assertTrue(responseEntity.getBody().toString().contains(farmList.toString()));
    }
}
