package com.example.ecommerce.controller.api;

import com.example.ecommerce.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cart")
public class CartApiController {

    private final CartService cartService;

    public CartApiController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Map<String, Object> getCart(HttpSession session) {
        BigDecimal total = cartService.getCartTotal(session);
        return Map.of(
            "items", cartService.getCartItems(session),
            "total", total,
            "currency", "INR"
        );
    }
}
