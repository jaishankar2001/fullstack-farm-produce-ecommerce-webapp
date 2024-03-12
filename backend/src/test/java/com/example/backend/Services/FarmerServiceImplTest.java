package com.example.backend.Services;

import com.example.backend.dto.request.AddFarmRequest;
import com.example.backend.dto.request.EditFarmRequest;
import com.example.backend.dto.request.FarmerOwnFarmRequest;
import com.example.backend.dto.response.FarmDto;
import com.example.backend.dto.response.GetFarmByIdResponse;
import com.example.backend.entities.Farms;
import com.example.backend.entities.Images;
import com.example.backend.entities.Product;
import com.example.backend.entities.User;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.FarmRepository;
import com.example.backend.repository.ImagesRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.impl.FarmerServiceImpl;
import com.example.backend.utils.Awsutils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import java.util.Optional;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class FarmerServiceImplTest {
    @BeforeEach
    public void Setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private ModelMapper modelMapperMock;

    @Mock
    private FarmRepository farmRepositoryMock;

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private ImagesRepository imagesRepositoryMock;

    @Mock
    private Awsutils awsutilsMock;;

    @InjectMocks
    private FarmerServiceImpl farmerServiceMock;

    @Test
    public void testAddFarm() {
        // Arrange
        AddFarmRequest farmRequest = new AddFarmRequest(/* provide necessary parameters */);
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testuser@example.com");
        User user = new User();
        user.setEmail("testuser@example.com");
        when(userRepositoryMock.findByEmail(anyString())).thenReturn(user);
        Farms farm = new Farms();
        when(modelMapperMock.map(any(), eq(Farms.class))).thenReturn(farm);
        when(farmRepositoryMock.save(any(Farms.class))).thenReturn(farm);
        List<Farms> userFarms = new ArrayList<>();
        userFarms.add(farm);
        when(farmRepositoryMock.findByUser(any(User.class))).thenReturn(userFarms);

        MultipartFile[] multipartFiles = {
                new MockMultipartFile("file1", "file1.jpg", "image/jpeg", new byte[0]),
                new MockMultipartFile("file2", "file2.jpg", "image/jpeg", new byte[0])
        };
        when(awsutilsMock.uploadFileToS3(any(MultipartFile.class), anyString(), anyInt())).thenReturn("dummy-url");

        // Act
        List<FarmDto> result = farmerServiceMock.addFarm(farmRequest, multipartFiles, principal);

        // Assert
        assertEquals(1, result.size()); // Ensure that the result contains one farm
    }

    @Test
    public void testEditFarm() {
        EditFarmRequest farmRequest = new EditFarmRequest(1, "New Name", "New Address", "New Description", 10.0, 20.0);
        MultipartFile[] multipartFiles = { new MockMultipartFile("file1", "file1.jpg", "image/jpeg", new byte[0]) };
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testuser@example.com");

        Farms existingFarm = new Farms();
        existingFarm.setId(1);
        existingFarm.setName("Old Name");
        existingFarm.setAddress("Old Address");
        existingFarm.setLng(5.0);
        existingFarm.setLat(15.0);
        existingFarm.setDescription("Old Description");
        Images image = new Images();
        image.setImg_url("image_url.jpg");
        List<Images> images = new ArrayList<>();
        images.add(image);
        existingFarm.setImages(images);

        when(farmRepositoryMock.findById(anyInt())).thenReturn(existingFarm);
        when(farmRepositoryMock.save(any(Farms.class))).thenReturn(existingFarm);

        // Act
        String result = farmerServiceMock.editFarm(farmRequest, multipartFiles, principal);

        System.out.println("HEREE?" + existingFarm.getName());
        // Assert
        assertEquals("Farm details edited successfully", result);
        assertEquals(farmRequest.getName(), existingFarm.getName());
        assertEquals(farmRequest.getAddress(), existingFarm.getAddress());
        assertEquals(farmRequest.getLng(), existingFarm.getLng(), 0.001);
        assertEquals(farmRequest.getLat(), existingFarm.getLat(), 0.001);
        assertEquals(farmRequest.getDescription(), existingFarm.getDescription());
        verify(awsutilsMock).deleteFilefromS3(eq("image_url.jpg"));
    }

    @Test
    public void testDeleteFarmNull() {
        when(farmRepositoryMock.findById(anyInt())).thenReturn(null);
        assertThrows(ApiRequestException.class, () -> farmerServiceMock.deleteFarm(anyInt()));
    }

    @Test
    public void testGetAllUserFarms() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testuser@example.com");

        User user = new User();
        user.setId(1);
        user.setEmail("testuser@example.com");

        Images image = new Images();
        image.setImg_url("image_url.jpg");
        List<Images> images = new ArrayList<>();
        images.add(image);

        Farms farm1 = new Farms();
        farm1.setId(1);
        farm1.setImages(images);

        Farms farm2 = new Farms();
        farm2.setId(2);
        farm2.setImages(images);
        List<Farms> userFarms = List.of(farm1, farm2);
        FarmerOwnFarmRequest farmerOwnFarmRequest = new FarmerOwnFarmRequest();

        when(userRepositoryMock.findByEmail(anyString())).thenReturn(user);
        when(farmRepositoryMock.findByUser(user)).thenReturn(userFarms);
        //
        List<FarmDto> result = farmerServiceMock.getFarms(farmerOwnFarmRequest, principal);
        // // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testGetAllUserFarmWithSearch() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testuser@example.com");

        User user = new User();
        user.setId(1);
        user.setEmail("testuser@example.com");

        Images image = new Images();
        image.setImg_url("image_url.jpg");
        List<Images> images = new ArrayList<>();
        images.add(image);

        Farms farm = new Farms();
        farm.setId(1);
        farm.setName("Farm Name");
        farm.setAddress("Farm Address");
        farm.setLat(123.456);
        farm.setLng(789.012);
        farm.setImages(images);

        List<Farms> userFarms = List.of(farm);
        FarmerOwnFarmRequest farmerOwnFarmRequest = new FarmerOwnFarmRequest();
        farmerOwnFarmRequest.setSearchTerm("Farm");

        when(userRepositoryMock.findByEmail(anyString())).thenReturn(user);
        when(farmRepositoryMock.findByUser(user)).thenReturn(userFarms);
        //
        List<FarmDto> result = farmerServiceMock.getFarms(farmerOwnFarmRequest, principal);
        // // Assert
        assertEquals(1, result.size());
    }

    @Test
    public void testGetAllFarmsWithSearch() {
        String farmName = "Farm 1";
        List<Farms> matchingFarms = createFarmsList(2); // Create a list of 2 farms with the provided name
        when(farmRepositoryMock.findByNameIgnoreCaseContaining(farmName)).thenReturn(matchingFarms);

        // Act
        List<FarmDto> result = farmerServiceMock.getAllFarms(farmName);

        // Assert
        assertEquals(2, result.size()); //
    }

    @Test
    public void testGetAllFarmsWithNullSearch() {
        String farmName = "Farm 1";
        List<Farms> matchingFarms = createFarmsList(2); // Create a list of 2 farms with the provided name
        when(farmRepositoryMock.findByNameIgnoreCaseContaining(farmName)).thenReturn(new ArrayList<>());
        when(farmRepositoryMock.findAll()).thenReturn(matchingFarms);

        // Act
        List<FarmDto> result = farmerServiceMock.getAllFarms(farmName);

        // Assert
        assertEquals(2, result.size()); //
    }

    @Test
    public void testGetAllFarmsWithoutSearch() {
        String farmName = "";
        List<Farms> matchingFarms = createFarmsList(3); // Create a list of 2 farms with the provided name
        when(farmRepositoryMock.findAll()).thenReturn(matchingFarms);

        // Act
        List<FarmDto> result = farmerServiceMock.getAllFarms(farmName);

        // Assert
        assertEquals(3, result.size()); //
    }

    @Test
    public void testGetFarmsWithNoUser() {
        FarmerOwnFarmRequest farmerOwnFarmRequest = new FarmerOwnFarmRequest();
        Principal principal = mock(Principal.class);
        when(userRepositoryMock.findByEmail(anyString())).thenReturn(null);
        assertThrows(ApiRequestException.class, () -> farmerServiceMock.getFarms(farmerOwnFarmRequest, principal));
    }

    @Test
    public void testGetFarmById() {
        int farmId = 1;
        Images image = new Images();
        image.setImg_url("image_url.jpg");
        List<Images> images = new ArrayList<>();
        images.add(image);

        Farms farm = new Farms();
        farm.setId(farmId);
        farm.setName("Farm Name");
        farm.setAddress("Farm Address");
        farm.setLat(123.456);
        farm.setLng(789.012);
        farm.setImages(images);

        when(farmRepositoryMock.findById(farmId)).thenReturn(farm);

        // Act
        GetFarmByIdResponse result = farmerServiceMock.getFarmById(farmId);

        // Assert
        assertNotNull(result);
        assertEquals("Farm Name", result.getName());
        assertEquals("Farm Address", result.getAddress());
        assertEquals(123.456, result.getLat(), 0.001);
        assertEquals(789.012, result.getLng(), 0.001);
        assertEquals(1, result.getImages().size());
    }

    @Test
    public void testGetFarmById_WhenFarmDoesNotExist() {
        // Arrange
        when(farmRepositoryMock.findById(anyInt())).thenReturn(null);

        // Act
        assertThrows(ApiRequestException.class, () -> farmerServiceMock.getFarmById(anyInt()));

    }

    @Test
    public void testDeleteFarm() {
        // Images
        List<Images> images = new ArrayList<>();
        Images image = new Images();
        image.setImg_url("image_url.jpg");
        images.add(image);

        Farms farm = new Farms();
        Product product1 = new Product();
        product1.setImages(images);

        Product product2 = new Product();
        product2.setImages(images);
        farm.setProduct(List.of(product1, product2));

        when(farmRepositoryMock.findById(anyInt())).thenReturn(farm);
        when(farmRepositoryMock.findById(anyInt())).thenReturn(farm);
        when(farmRepositoryMock.findById(any(Integer.class))).thenReturn(Optional.of(farm));

        doNothing().when(productRepositoryMock).deleteById(anyInt());
        doNothing().when(imagesRepositoryMock).deleteById(anyInt());
        doNothing().when(farmRepositoryMock).deleteById(anyInt());

        farmerServiceMock.deleteFarm(anyInt());

        // Assert
        verify(productRepositoryMock, times(2)).deleteById(anyInt()); // Verify product deletion
        verify(imagesRepositoryMock, times(2)).deleteById(anyInt()); // Verify image deletion (each product may have
                                                                     // multiple images)
        verify(farmRepositoryMock, times(1)).deleteById(anyInt()); // Verify farm deletion
    }

    private List<Farms> createFarmsList(int count) {
        List<Farms> farms = new ArrayList<>();
        Images image = new Images();
        image.setImg_url("image_url.jpg");
        List<Images> images = new ArrayList<>();
        images.add(image);
        for (int i = 0; i < count; i++) {
            Farms farm = new Farms();
            farm.setId((i + 1));
            farm.setName("Farm " + (i + 1));
            farm.setImages(images);
            farms.add(farm);
        }
        return farms;
    }
}
