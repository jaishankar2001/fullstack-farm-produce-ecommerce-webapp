package com.example.backend.ControllerTests;

import com.example.backend.controller.CategoryController;
import com.example.backend.entities.Category;
import com.example.backend.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CategoryControllerTest {
    @Mock
    ResponseEntity responseEntity;
    @InjectMocks
    CategoryController categoryController;
    @Mock
    Category category;
    @Mock
    CategoryService categoryService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        responseEntity = categoryController.getCategories();
    }

    @Test
    void testGetCategories(){
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    void tetsGetCategoriesBody(){
        List<Category> responseList = categoryService.listcategories();
        assertTrue(responseEntity.getBody().toString().contains(responseList.toString()));
    }
}
