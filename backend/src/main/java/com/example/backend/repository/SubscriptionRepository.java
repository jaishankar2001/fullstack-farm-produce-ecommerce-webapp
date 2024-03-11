package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entities.Days;
import com.example.backend.entities.Subscription;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    List<Subscription> findAllByUserIdAndProductId(int userId, int productId);

    List<Subscription> findByDays(Days days);

}