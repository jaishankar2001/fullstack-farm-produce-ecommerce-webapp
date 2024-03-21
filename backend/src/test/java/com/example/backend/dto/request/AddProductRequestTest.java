package com.example.backend.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddProductRequestTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testValidation() {
        AddProductRequest request = new AddProductRequest();

        // Validate the object
        Set<ConstraintViolation<AddProductRequest>> violations = validator.validate(request);


        // Assert that each violation corresponds to the correct field and message
        for (ConstraintViolation<AddProductRequest> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            assertTrue(fieldName.contains("productName") || fieldName.contains("productDescription")
                    || fieldName.contains("price") || fieldName.contains("stock")
                    || fieldName.contains("unit") || fieldName.contains("category_id")
                    || fieldName.contains("farm_id"));

            assertTrue(violation.getMessage().contains("required"));
        }
    }

    @Test
    public void testGetterAndSetter() {
        AddProductRequest request = new AddProductRequest();

        // Set values using setter methods
        request.setProductName("Product Name");
        request.setProductDescription("Product Description");
        request.setPrice(10.99);
        request.setStock(100);
        request.setUnit("kg");
        request.setPrebook(true);
        request.setCategory_id(123);
        request.setFarm_id(456);

        // Verify values using getter methods
        assertEquals("Product Name", request.getProductName());
        assertEquals("Product Description", request.getProductDescription());
        assertEquals(10.99, request.getPrice());
        assertEquals(100, request.getStock());
        assertEquals("kg", request.getUnit());
        assertTrue(request.isPrebook());
        assertEquals(123, request.getCategory_id());
        assertEquals(456, request.getFarm_id());
    }
}
