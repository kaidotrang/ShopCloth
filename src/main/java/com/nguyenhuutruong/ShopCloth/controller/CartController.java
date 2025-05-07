package com.nguyenhuutruong.ShopCloth.controller;

import com.nguyenhuutruong.ShopCloth.model.CartItem;
import com.nguyenhuutruong.ShopCloth.model.User;
import com.nguyenhuutruong.ShopCloth.service.CartService;
import com.nguyenhuutruong.ShopCloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService; // thêm dòng này

    @PostMapping("/add")
    public String addToCart(@RequestParam("productVariantId") Long productVariantId,
                            @RequestParam("quantity") Integer quantity,
                            Authentication authentication) {

        String email = authentication.getName(); // lấy email hoặc username từ Security
        User user = userService.findByEmail(email); // lấy User từ DB

        cartService.addProductToCart(user, productVariantId, quantity);

        return "redirect:/cart/view";
    }

    @GetMapping("/view")
    public String viewCart(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        List<CartItem> cartItems = cartService.getCartItemsByUser(user);
        model.addAttribute("cartItems", cartItems);

        return "cart-view"; // cart/view.html trong templates
    }
}



