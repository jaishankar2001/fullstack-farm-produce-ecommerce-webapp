package com.example.backend.Controller;

import com.example.backend.controller.FarmerController;
import com.example.backend.dto.request.AddFarmRequest;
import com.example.backend.dto.request.EditFarmRequest;
import com.example.backend.dto.request.FarmerOwnFarmRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.FarmDto;
import com.example.backend.dto.response.GetFarmByIdResponse;
import com.example.backend.services.FarmerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class FarmerControllerTest {
    @Mock
    private FarmerService farmerServiceMock;

    @InjectMocks
    private FarmerController farmerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOwnFarms() {
        FarmerOwnFarmRequest farmerOwnFarmRequest = new FarmerOwnFarmRequest();

        List<FarmDto> farmList = Arrays.asList(new FarmDto(), new FarmDto());

        Principal principal = mock(Principal.class);
        // ARRANGE
        when(farmerServiceMock.getFarms(farmerOwnFarmRequest, principal)).thenReturn(farmList);

        ResponseEntity<List<FarmDto>> responseEntity = farmerController.getFarms(farmerOwnFarmRequest, principal);

        // Verify response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(farmList, responseEntity.getBody());
    }

    @Test
    public void testAddFarm() {
        // ARRANGE
        AddFarmRequest farmRequest = new AddFarmRequest();
        List<FarmDto> farmList = Arrays.asList(new FarmDto(), new FarmDto());
        byte[] content = new byte[1];
        MockMultipartFile mockMultipartFile = new MockMultipartFile("Dummy-file", content);

        Principal principal = mock(Principal.class);
        when(farmerServiceMock.addFarm(farmRequest, new MockMultipartFile[] { mockMultipartFile }, principal))
                .thenReturn(farmList);

        ResponseEntity<Map> responseEntity = farmerController.addFarm(farmRequest,
                new MockMultipartFile[] { mockMultipartFile }, principal);

        // ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(farmList, responseEntity.getBody().get("AllFarm"));
    }

    @Test
    public void testEditFarm() {
        byte[] content = new byte[1];
        Principal principal = mock(Principal.class);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("Dummy-file", content);

        EditFarmRequest farmRequest = new EditFarmRequest(/* construct request object */);
        String editFarmResponse = "Success";

        // ARRANGE
        when(farmerServiceMock.editFarm(farmRequest, new MockMultipartFile[] { mockMultipartFile }, principal))
                .thenReturn(editFarmResponse);

        ResponseEntity<Map> responseEntity = farmerController.editFarm(farmRequest,
                new MultipartFile[] { mockMultipartFile }, principal);

        // Verify response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(principal.getName(), responseEntity.getBody().get("message"));
        assertEquals(editFarmResponse, responseEntity.getBody().get("Result"));
    }

    @Test
    public void testDeleteFarm() {
        int farmId = 1;

        doNothing().when(farmerServiceMock).deleteFarm(farmId);

        ResponseEntity<ApiResponse> responseEntity = farmerController.deleteFarm(farmId);

        // ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Farm deleted successfully", responseEntity.getBody().getMessage());
    }

    @Test
    public void testGetFarmById() {
        int farmId = 1;
        GetFarmByIdResponse farmResponse = new GetFarmByIdResponse();

        when(farmerServiceMock.getFarmById(farmId)).thenReturn(farmResponse);

        ResponseEntity<GetFarmByIdResponse> responseEntity = farmerController.getFarmById(farmId);

        // ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(farmResponse, responseEntity.getBody());

    }
}
