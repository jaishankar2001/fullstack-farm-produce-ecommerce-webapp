package com.example.backend.entities;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "customer_wallet_history")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    private Double Amount_Added;
    private String Paymnent_Method_Reference;

    @Temporal(TemporalType.DATE)
    private Date Transaction_Date;

}
