package com.example.ecommerce.service;

import com.example.ecommerce.domain.CartItem;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    List<CartItem> getCartItems(HttpSession session);

    BigDecimal getCartTotal(HttpSession session);

    int getTotalItemCount(HttpSession session);

    void addProduct(HttpSession session, Long productId, int quantity);

    void updateQuantity(HttpSession session, Long productId, int quantity);

    void removeProduct(HttpSession session, Long productId);

    void clearCart(HttpSession session);
}