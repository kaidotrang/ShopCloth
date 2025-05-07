package com.nguyenhuutruong.ShopCloth.controller;

import com.nguyenhuutruong.ShopCloth.model.*;
import com.nguyenhuutruong.ShopCloth.repository.OrderItemRepository;
import com.nguyenhuutruong.ShopCloth.repository.OrderRepository;
import com.nguyenhuutruong.ShopCloth.repository.PaymentRepository;
import com.nguyenhuutruong.ShopCloth.repository.ProductVariantRepository;
import com.nguyenhuutruong.ShopCloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ProductVariantRepository variantRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/placeFromProduct/{variantId}")
    public String placeOrderFromProduct(@PathVariable Long variantId,
                                        @RequestParam("quantity") Integer quantity,
                                        Authentication authentication) {

        String email = authentication.getName();
        User currentUser = userService.findByEmail(email);

        ProductVariant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phiên bản sản phẩm"));

        Orders order = new Orders();
        order.setUser(currentUser);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Orders.OrderStatus.PENDING);
        order.setAddress("");
        order.setPhone("");
        order.setTotalAmount(variant.getPrice().multiply(BigDecimal.valueOf(quantity)));
        orderRepository.save(order);

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProductVariant(variant);
        item.setQuantity(quantity);
        item.setPrice(variant.getPrice());
        orderItemRepository.save(item);

        return "redirect:/order/checkout/" + order.getId(); // orderId có trong URL
    }

    @GetMapping("/checkout/{id}")
    public String checkout(@PathVariable Long id, Model model) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));
        model.addAttribute("order", order);
        return "order-checkout"; // Tên view checkout.html trong thư mục templates/order
    }

    @PostMapping("/confirm/{id}")
    public String confirmOrder(@PathVariable Long id,
                               @RequestParam("address") String address,
                               @RequestParam("phone") String phone,
                               @RequestParam("paymentMethod") String paymentMethod) {

        // Lấy đơn hàng từ cơ sở dữ liệu
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        // Cập nhật thông tin giao hàng cho đơn hàng
        order.setAddress(address);
        order.setPhone(phone);
        order.setStatus(Orders.OrderStatus.PENDING); // Đặt trạng thái đơn hàng thành PENDING
        orderRepository.save(order);

        // Tạo và lưu thông tin thanh toán vào bảng Payment
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus(Payment.PaymentStatus.PENDING); // Ban đầu trạng thái thanh toán là PENDING
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment); // Lưu thông tin thanh toán vào bảng Payment

        // Sau khi xác nhận, chuyển đến trang thành công
        return "redirect:/order/success/" + order.getId(); // Chuyển đến trang thành công
    }

    @GetMapping("/success/{id}")
    public String success(@PathVariable Long id, Model model) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        // Thêm đơn hàng vào model để hiển thị thông tin cho người dùng
        model.addAttribute("order", order);

        // Trả về view thành công, ví dụ: order-success.html
        return "order-success";
    }

    @PostMapping("/placeFromCart")
    public String placeOrderFromCart(Authentication authentication) {
        String email = authentication.getName();
        User currentUser = userService.findByEmail(email);

        // Lấy danh sách các sản phẩm trong giỏ hàng của người dùng
        // (Ví dụ nếu bạn có service CartService)
        List<CartItem> cartItems = currentUser.getCartItems(); // hoặc từ cartService.getItemsByUser(currentUser)

        if (cartItems == null || cartItems.isEmpty()) {
            return "redirect:/cart?empty=true"; // Giỏ hàng trống
        }

        // Tạo đơn hàng mới
        Orders order = new Orders();
        order.setUser(currentUser);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Orders.OrderStatus.PENDING);
        order.setAddress("");
        order.setPhone("");

        BigDecimal total = cartItems.stream()
                .map(item -> item.getProductVariant().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(total);
        orderRepository.save(order);

        // Tạo các OrderItem từ CartItem
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductVariant(cartItem.getProductVariant());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProductVariant().getPrice());
            orderItemRepository.save(orderItem);
        }

        // Xóa giỏ hàng sau khi tạo đơn hàng
        cartItems.clear(); // nếu là danh sách từ user
        // hoặc gọi: cartService.clearCart(currentUser);

        return "redirect:/order/checkout/" + order.getId();
    }

}

