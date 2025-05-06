package com.nguyenhuutruong.ShopCloth.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private Integer discountPercent;
    private LocalDate validFrom;
    private LocalDate validUntil;

    @Enumerated(EnumType.STRING)
    private VoucherStatus status;  // 'active', 'inactive', 'expired'

    public enum VoucherStatus {
        ACTIVE, INACTIVE, EXPIRED
    }

    @OneToMany(mappedBy = "voucher", fetch = FetchType.LAZY)
    private List<Orders> orders;
}
