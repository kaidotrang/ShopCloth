package com.nguyenhuutruong.ShopCloth.service;

import com.nguyenhuutruong.ShopCloth.model.Product;
import com.nguyenhuutruong.ShopCloth.repository.ProductRepository;
import com.nguyenhuutruong.ShopCloth.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id: " + id));
    }

    @Override
    public List<Product> search(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    @Override
    public Product save(Product p) {
        return productRepository.save(p);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

}
