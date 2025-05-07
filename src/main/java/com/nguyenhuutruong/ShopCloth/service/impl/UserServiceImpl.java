package com.nguyenhuutruong.ShopCloth.service.impl;

import com.nguyenhuutruong.ShopCloth.model.User;
import com.nguyenhuutruong.ShopCloth.repository.UserRepository;
import com.nguyenhuutruong.ShopCloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}

