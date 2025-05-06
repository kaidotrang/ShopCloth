package com.nguyenhuutruong.ShopCloth.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_variant_id", nullable = false)
    private ProductVariant productVariant;

    private Integer quantity;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
}
