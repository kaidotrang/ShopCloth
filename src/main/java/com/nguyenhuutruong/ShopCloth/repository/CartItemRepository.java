package com.nguyenhuutruong.ShopCloth.repository;

import com.nguyenhuutruong.ShopCloth.model.CartItem;
import com.nguyenhuutruong.ShopCloth.model.ProductVariant;
import com.nguyenhuutruong.ShopCloth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProductVariant(User user, ProductVariant productVariant);

    List<CartItem> findByUser(User user);
}