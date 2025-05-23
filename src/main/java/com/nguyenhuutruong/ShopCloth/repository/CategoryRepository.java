package com.nguyenhuutruong.ShopCloth.repository;

import com.nguyenhuutruong.ShopCloth.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
