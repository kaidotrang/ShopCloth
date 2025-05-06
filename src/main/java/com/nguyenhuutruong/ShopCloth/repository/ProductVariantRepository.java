package com.nguyenhuutruong.ShopCloth.repository;

import com.nguyenhuutruong.ShopCloth.model.Product;
import com.nguyenhuutruong.ShopCloth.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    // Tìm kiếm ProductVariant theo Product và size, color
    ProductVariant findByProductAndSizeAndColor(Product product, String size, String color);

    // Nếu bạn muốn truy vấn dựa trên các thuộc tính khác, bạn có thể tạo thêm các phương thức như sau:
    List<ProductVariant> findByProduct(Product product);

    List<ProductVariant> findByColor(String color);

    List<ProductVariant> findBySize(String size);

}

