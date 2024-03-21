package com.example.backend.dto.response;

import org.junit.jupiter.api.Test;

import com.example.backend.entities.SubscriptionName;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GetSubscriptionResponseTest {
    @Test
    public void testGetterAndSetter() {
        GetSubscriptionResponse response = new GetSubscriptionResponse();

        SubscriptionName name = SubscriptionName.DAILY;
        ArrayList<String> days = new ArrayList<>();
        days.add("Monday");
        LocalDateTime subscriptionDate = LocalDateTime.now();
        ProductSubscriptionResponse product = new ProductSubscriptionResponse();
        int productId = 123;
        String customerName = "John Doe";

        response.setName(name);
        response.setDays(days);
        response.setSubscriptionDate(subscriptionDate);
        response.setProduct(product);
        response.setProductId(productId);
        response.setCustomerName(customerName);

        assertEquals(name, response.getName());
        assertEquals(days, response.getDays());
        assertEquals(subscriptionDate, response.getSubscriptionDate());
        assertEquals(product, response.getProduct());
        assertEquals(productId, response.getProductId());
        assertEquals(customerName, response.getCustomerName());
    }
}
