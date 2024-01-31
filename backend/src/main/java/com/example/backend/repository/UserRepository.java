package com.example.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.backend.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
