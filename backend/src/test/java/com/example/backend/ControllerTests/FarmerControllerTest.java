package com.example.backend.ControllerTests;

import com.example.backend.controller.FarmerController;
import com.example.backend.dto.request.AddFarmRequest;
import com.example.backend.dto.request.EditFarmRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.FarmDto;
import com.example.backend.services.FarmerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FarmerControllerTest {

    @InjectMocks
    private FarmerController farmerController;

    @Mock
    private FarmerService farmerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFarm() {
        AddFarmRequest farmRequest = new AddFarmRequest();
        MultipartFile[] files = new MockMultipartFile[1];
        Principal principal = mock(Principal.class);

        List<FarmDto> expectedFarmList = new ArrayList<>();
        when(farmerService.addFarm(farmRequest, files, principal)).thenReturn(expectedFarmList);

        ResponseEntity<Map> responseEntity = farmerController.addFarm(farmRequest, files, principal);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void testEditFarm() {
        // Mocking parameters
        EditFarmRequest farmRequest = new EditFarmRequest();
        MultipartFile[] files = new MockMultipartFile[1];
        Principal principal = mock(Principal.class);

        // Mocking farmerService.editFarm() method
        String expectedEditFarmResponse = "Success"; // Assuming editFarmResponse returns "Success"
        when(farmerService.editFarm(farmRequest, files, principal)).thenReturn(expectedEditFarmResponse);

        // Call the method under test
        ResponseEntity<Map> responseEntity = farmerController.editFarm(farmRequest, files, principal);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void testDeleteFarm() {
        int farmId = 1;
        doNothing().when(farmerService).deleteFarm(farmId);

        // Act
        ResponseEntity<ApiResponse> responseEntity = farmerController.deleteFarm(farmId);

        // Assert
        String actualMessage = responseEntity.getBody().getMessage();
        assertEquals("Farm deleted successfully", actualMessage);

    }

}
