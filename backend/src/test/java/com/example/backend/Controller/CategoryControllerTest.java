package com.example.backend.Controller;

import com.example.backend.controller.CategoryController;
import com.example.backend.entities.Category;
import com.example.backend.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class CategoryControllerTest {

    @Mock
    private CategoryService categoryServiceMock;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCategories() {
        List<Category> categoryList = new ArrayList<>();
        when(categoryServiceMock.listcategories()).thenReturn(categoryList);

        ResponseEntity<List<Category>> response =  categoryController.getCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryList, response.getBody());
        verify(categoryServiceMock, times(1)).listcategories();
    }
}
