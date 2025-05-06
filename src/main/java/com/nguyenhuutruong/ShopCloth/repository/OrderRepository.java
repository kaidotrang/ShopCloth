package com.nguyenhuutruong.ShopCloth.repository;
import com.nguyenhuutruong.ShopCloth.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserId(Long userId);

    List<Orders> findByVoucherId(Long voucherId);
}