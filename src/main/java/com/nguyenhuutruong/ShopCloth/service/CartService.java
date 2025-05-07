package com.nguyenhuutruong.ShopCloth.service;

import com.nguyenhuutruong.ShopCloth.model.CartItem;
import com.nguyenhuutruong.ShopCloth.model.User;

import java.util.List;

public interface CartService {
    void addProductToCart(User user, Long productVariantId, Integer quantity);

    List<CartItem> getCartItemsByUser(User user);
}
