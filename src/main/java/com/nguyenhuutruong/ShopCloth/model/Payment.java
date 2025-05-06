package com.nguyenhuutruong.ShopCloth.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;  // 'pending', 'paid', 'failed'

    private LocalDateTime paymentDate;

    public enum PaymentStatus {
        PENDING, PAID, FAILED
    }
}
