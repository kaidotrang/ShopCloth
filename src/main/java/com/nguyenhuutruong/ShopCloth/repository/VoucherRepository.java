package com.nguyenhuutruong.ShopCloth.repository;
import com.nguyenhuutruong.ShopCloth.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Voucher findByCode(String code);
}