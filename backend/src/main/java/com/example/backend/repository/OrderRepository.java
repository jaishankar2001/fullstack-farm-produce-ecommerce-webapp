package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entities.Order;
import com.example.backend.entities.User;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUser(User user);

}
