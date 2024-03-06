package com.example.backend.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;


public class AddProductRequestTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testProductNameNotBlank() {
        AddProductRequest request = new AddProductRequest();
        request.setUnit("1");
        Set<ConstraintViolation<AddProductRequest>> violations = validator.validate(request);
        assertEquals("name is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testProductDescriptionNotBlank() {
        AddProductRequest request = new AddProductRequest();
        request.setProductDescription("");
        Set<ConstraintViolation<AddProductRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("productDescription is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testPriceNotNull() {
        AddProductRequest request = new AddProductRequest();
        request.setPrice(null);
        Set<ConstraintViolation<AddProductRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("price is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testStockNotNull() {
        AddProductRequest request = new AddProductRequest();
        request.setStock(0);
        Set<ConstraintViolation<AddProductRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("stock is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testUnitNotBlank() {
        AddProductRequest request = new AddProductRequest();
        request.setUnit("");
        Set<ConstraintViolation<AddProductRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("unit is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testCategoryIdNotNull() {
        AddProductRequest request = new AddProductRequest();
        request.setCategory_id(0);
        Set<ConstraintViolation<AddProductRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("category_id is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testFarmIdNotNull() {
        AddProductRequest request = new AddProductRequest();
        request.setFarm_id(0);
        Set<ConstraintViolation<AddProductRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("farm_id is required", violations.iterator().next().getMessage());
    }
}
