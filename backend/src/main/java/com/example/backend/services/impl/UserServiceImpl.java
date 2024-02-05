package com.example.backend.services.impl;

import com.example.backend.entities.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
