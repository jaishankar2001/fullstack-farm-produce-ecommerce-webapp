package com.example.backend.dto.request;

import org.junit.jupiter.api.Test;

import com.example.backend.entities.SubscriptionName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductSubscribeRequestTest {

    @Test
    public void testGetterAndSetter() {
        // ARRANGE
        ProductSubscribeRequest request = new ProductSubscribeRequest();

        SubscriptionName name = SubscriptionName.DAILY;
        int mon = 1;
        int tue = 1;
        int wed = 1;
        int thu = 1;
        int fri = 1;
        int sat = 1;
        int sun = 1;
        int farmId = 123;
        int productId = 456;

        request.setName(name);
        request.setMon(mon);
        request.setTue(tue);
        request.setWed(wed);
        request.setThu(thu);
        request.setFri(fri);
        request.setSat(sat);
        request.setSun(sun);
        request.setFarm_id(farmId);
        request.setProduct_id(productId);

        // ASSERT
        assertEquals(name, request.getName());
        assertEquals(mon, request.getMon());
        assertEquals(tue, request.getTue());
        assertEquals(wed, request.getWed());
        assertEquals(thu, request.getThu());
        assertEquals(fri, request.getFri());
        assertEquals(sat, request.getSat());
        assertEquals(sun, request.getSun());
        assertEquals(farmId, request.getFarm_id());
        assertEquals(productId, request.getProduct_id());
    }
}