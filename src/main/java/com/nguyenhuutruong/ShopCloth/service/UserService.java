package com.nguyenhuutruong.ShopCloth.service;

import com.nguyenhuutruong.ShopCloth.model.User;

public interface UserService {
    User findByEmail(String email);
}