package com.nguyenhuutruong.ShopCloth.service.impl;

import com.nguyenhuutruong.ShopCloth.model.Category;

import java.util.List;

public interface CategoryService {
    Category findById(Long id);
    List<Category> findAll();
    Category save(Category category);
    void deleteById(Long id);
}
