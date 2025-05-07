package com.nguyenhuutruong.ShopCloth.service;

import com.nguyenhuutruong.ShopCloth.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Long id);

    List<Product> search(String keyword);

    Product save(Product p);

    void deleteById(Long id);

    List<Product> findByCategoryId(Long categoryId);

}
