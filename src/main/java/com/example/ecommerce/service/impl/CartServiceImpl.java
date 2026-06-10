package com.example.ecommerce.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.ecommerce.domain.CartItem;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.CartService;

import jakarta.servlet.http.HttpSession;

@Service
public class CartServiceImpl implements CartService {

    private static final String CART_SESSION_KEY = "CART";

    private final ProductRepository productRepository;

    public CartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<CartItem> getCartItems(HttpSession session) {
        Map<Long, Integer> cart = getCart(session);
        List<CartItem> items = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            productRepository.findById(entry.getKey()).ifPresent(product -> items.add(new CartItem(product, entry.getValue())));
        }
        return items;
    }

    @Override
    public BigDecimal getCartTotal(HttpSession session) {
        return getCartItems(session).stream()
            .map(CartItem::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public int getTotalItemCount(HttpSession session) {
        return getCart(session).values().stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public void addProduct(HttpSession session, Long productId, int quantity) {
        if (quantity < 1) {
            quantity = 1;
        }
        Map<Long, Integer> cart = getCart(session);
        cart.merge(productId, quantity, Integer::sum);
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    @Override
    public void updateQuantity(HttpSession session, Long productId, int quantity) {
        Map<Long, Integer> cart = getCart(session);
        if (quantity <= 0) {
            cart.remove(productId);
        } else {
            cart.put(productId, quantity);
        }
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    @Override
    public void removeProduct(HttpSession session, Long productId) {
        Map<Long, Integer> cart = getCart(session);
        cart.remove(productId);
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    @Override
    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }

    private Map<Long, Integer> getCart(HttpSession session) {
        Object value = session.getAttribute(CART_SESSION_KEY);
        if (value instanceof Map<?, ?> cartMap) {
            Map<Long, Integer> cart = new LinkedHashMap<>();
            cartMap.forEach((key, cartValue) -> cart.put(((Number) key).longValue(), ((Number) cartValue).intValue()));
            return cart;
        }
        Map<Long, Integer> cart = new LinkedHashMap<>();
        session.setAttribute(CART_SESSION_KEY, cart);
        return cart;
    }
}