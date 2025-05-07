package com.nguyenhuutruong.ShopCloth.service.impl;

import com.nguyenhuutruong.ShopCloth.model.CartItem;
import com.nguyenhuutruong.ShopCloth.model.ProductVariant;
import com.nguyenhuutruong.ShopCloth.model.User;
import com.nguyenhuutruong.ShopCloth.repository.CartItemRepository;
import com.nguyenhuutruong.ShopCloth.repository.ProductVariantRepository;
import com.nguyenhuutruong.ShopCloth.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Override
    public void addProductToCart(User user, Long productVariantId, Integer quantity) {
        ProductVariant productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy biến thể sản phẩm"));

        BigDecimal price = productVariant.getPrice(); // giả sử có thuộc tính này

        CartItem existingItem = cartItemRepository.findByUserAndProductVariant(user, productVariant);

        if (existingItem == null) {
            CartItem newItem = new CartItem(user, productVariant, quantity, price);
            cartItemRepository.save(newItem);
        } else {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);
        }
    }

    @Override
    public List<CartItem> getCartItemsByUser(User user) {
        return cartItemRepository.findByUser(user);
    }
}

