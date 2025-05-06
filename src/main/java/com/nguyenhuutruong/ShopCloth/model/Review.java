package com.nguyenhuutruong.ShopCloth.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
}
