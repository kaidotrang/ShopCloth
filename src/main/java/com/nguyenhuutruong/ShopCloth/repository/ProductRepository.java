package com.nguyenhuutruong.ShopCloth.repository;

import com.nguyenhuutruong.ShopCloth.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByNameContaining(String keyword);
}
