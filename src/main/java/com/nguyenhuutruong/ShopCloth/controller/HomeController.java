package com.nguyenhuutruong.ShopCloth.controller;

import com.nguyenhuutruong.ShopCloth.model.Category;
import com.nguyenhuutruong.ShopCloth.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService; // Giả sử bạn có một service để lấy danh mục từ cơ sở dữ liệu.

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories); // Truyền danh sách các danh mục vào model
        return "index"; // Tên của view (html)
    }
}

