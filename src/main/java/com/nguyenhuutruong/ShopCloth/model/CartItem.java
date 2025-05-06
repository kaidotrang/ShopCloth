package com.nguyenhuutruong.ShopCloth.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_variant_id", nullable = false)
    private ProductVariant productVariant;

    private Integer quantity;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;


    public CartItem() {
    }

    public CartItem(User user, ProductVariant productVariant, Integer quantity, BigDecimal price) {
        this.user = user;
        this.productVariant = productVariant;
        this.quantity = quantity;
        this.price = price;
    }
}
