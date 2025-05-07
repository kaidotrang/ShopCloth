package com.nguyenhuutruong.ShopCloth.controller;

import com.nguyenhuutruong.ShopCloth.model.Product;
import com.nguyenhuutruong.ShopCloth.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product-detail";
    }

    @GetMapping
    public String listProductsByCategory(@RequestParam(value = "t", required = false) Long categoryId, Model model) {
        List<Product> products;

        // Kiểm tra xem có tham số "t" trong URL hay không để lọc theo danh mục
        if (categoryId != null) {
            products = productService.findByCategoryId(categoryId); // Lọc sản phẩm theo categoryId
        } else {
            products = productService.findAll(); // Lấy tất cả sản phẩm nếu không có categoryId
        }

        model.addAttribute("products", products);
        return "product-list";
    }
}