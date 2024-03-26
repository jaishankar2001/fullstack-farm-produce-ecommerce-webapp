package com.example.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entities.Order;
import com.example.backend.entities.User;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUser(User user);

    public List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
