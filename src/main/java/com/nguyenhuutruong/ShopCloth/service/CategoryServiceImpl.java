package com.nguyenhuutruong.ShopCloth.service;

import com.nguyenhuutruong.ShopCloth.model.Category;
import com.nguyenhuutruong.ShopCloth.repository.CategoryRepository;
import com.nguyenhuutruong.ShopCloth.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository; // Giả sử bạn có một repository để truy vấn cơ sở dữ liệu.


    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục với id: " + id));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
