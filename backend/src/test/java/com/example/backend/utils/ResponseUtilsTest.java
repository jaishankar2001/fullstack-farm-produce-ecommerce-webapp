package com.example.backend.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.backend.dto.response.*;
import com.example.backend.entities.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ResponseUtilsTest {

    @Mock
    private Farms farms;

    @Mock
    private Product product;

    @Mock
    private User user;

    @Mock
    private Order order;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConvertFarmResponse() {
        when(farms.getId()).thenReturn(1);
        when(farms.getName()).thenReturn("Farm Name");
        when(farms.getAddress()).thenReturn("Farm Address");
        when(farms.getDescription()).thenReturn("Farm Description");
        when(farms.getLat()).thenReturn(10.1234);
        when(farms.getLng()).thenReturn(20.5678);

        FarmDto farmDto = ResponseUtils.convertFarmResponse(farms);

        assertEquals(farms.getId(), farmDto.getId());
        assertEquals(farms.getName(), farmDto.getName());
        assertEquals(farms.getAddress(), farmDto.getAddress());
        assertEquals(farms.getDescription(), farmDto.getDescription());
        assertEquals(farms.getLat(), farmDto.getLat(), 0.0001);
        assertEquals(farms.getLng(), farmDto.getLng(), 0.0001);
    }

    @Test
    public void testConvertProductResponse() {
        when(product.getId()).thenReturn(1);
        when(product.getProductName()).thenReturn("Product Name");
        when(product.getStock()).thenReturn(100);
        when(product.getPrice()).thenReturn(10.50);
        when(product.getUnit()).thenReturn("kg");
        when(product.getProductDescription()).thenReturn("Product Description");

        Category category = new Category();
        category.setId(1);
        category.setName("Category Name");

        when(product.getCategory()).thenReturn(category);

        List<Images> imagesList = new ArrayList<>();
        Images image = new Images();
        imagesList.add(image);
        when(product.getImages()).thenReturn(imagesList);

        ProductDto productDto = ResponseUtils.convertProductResponse(product);

        assertEquals(product.getId(), productDto.getId());
        assertEquals(product.getProductName(), productDto.getProductName());
        assertEquals(product.getStock(), productDto.getStock());
        assertEquals(product.getPrice(), productDto.getPrice(), 0.01);
        assertEquals(product.getUnit(), productDto.getUnit());
        assertEquals(product.getProductDescription(), productDto.getProductDescription());
        assertEquals(product.getCategory().getId(), productDto.getCategory().getId());
        assertEquals(product.getCategory().getName(), productDto.getCategory().getName());
    }

    @Test
    public void testConvertUserResponse() {
        when(user.getId()).thenReturn(1);
        when(user.getEmail()).thenReturn("user@example.com");
        when(user.getFirstname()).thenReturn("John");
        when(user.getLastname()).thenReturn("Doe");
        when(user.getRole()).thenReturn(Role.valueOf("CUSTOMER"));

        UserDTO userDTO = ResponseUtils.convertUserResponse(user);

        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getFirstname(), userDTO.getFirstname());
        assertEquals(user.getLastname(), userDTO.getLastname());
        assertEquals(user.getRole(), userDTO.getRole());
    }

    @Test
    public void testConvertOrderResponse() {
        when(order.getId()).thenReturn(1);
        String str = "2001-10-26 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        when(order.getOrderDate()).thenReturn(LocalDateTime.parse(str,formatter));
        when(order.getOrderValue()).thenReturn(100.0);
        when(order.getQuantity()).thenReturn(5);
        when(order.getOrderPaymentMethod()).thenReturn("Cash");

        Product product = mock(Product.class);
        when(product.getProductName()).thenReturn("Product Name");
        when(product.getProductDescription()).thenReturn("Product Description");

        Images image = mock(Images.class);

        List<Images> imagesList = new ArrayList<>();
        imagesList.add(image);
        when(product.getImages()).thenReturn(imagesList);

        when(order.getProduct()).thenReturn(product);

        Farms farms = mock(Farms.class);
        when(farms.getName()).thenReturn("Farm Name");

        when(order.getFarm()).thenReturn(farms);

        OrderDto orderDto = ResponseUtils.convertOrderResponse(order);

        assertEquals(order.getId(), orderDto.getId());
        assertEquals(order.getProduct(), orderDto.getProduct());
        assertEquals(order.getProduct().getProductName(), orderDto.getProductName());
        assertEquals(order.getProduct().getProductDescription(), orderDto.getProductDescription());
        assertEquals(order.getFarm(), orderDto.getFarm());
        assertEquals(order.getFarm().getName(), orderDto.getFarmName());
    }

}
