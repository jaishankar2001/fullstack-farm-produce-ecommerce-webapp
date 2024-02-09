package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entities.Wallet;
import java.util.*;
import com.example.backend.entities.UserMeta;




public interface WalletRepository extends JpaRepository<Wallet, Integer>{

    Wallet findByUserId(UserMeta userMeta);

}
