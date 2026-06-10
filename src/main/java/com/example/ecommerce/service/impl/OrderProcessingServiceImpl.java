package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.CartItem;
import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.OrderItem;
import com.example.ecommerce.domain.OrderStatus;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.OrderProcessingService;
import com.example.ecommerce.web.dto.CheckoutForm;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {

    private final CartService cartService;
    private final com.example.ecommerce.repository.ProductRepository productRepository;
    private final com.example.ecommerce.repository.OrderRepository orderRepository;

    public OrderProcessingServiceImpl(CartService cartService,
                                      com.example.ecommerce.repository.ProductRepository productRepository,
                                      com.example.ecommerce.repository.OrderRepository orderRepository) {
        this.cartService = cartService;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order placeOrder(CheckoutForm checkoutForm, HttpSession session) {
        List<CartItem> cartItems = cartService.getCartItems(session);
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        Order order = new Order();
        order.setCustomerName(checkoutForm.getCustomerName());
        order.setMobileNumber(checkoutForm.getMobileNumber());
        order.setEmail(checkoutForm.getEmail());
        order.setShippingAddress(checkoutForm.getShippingAddress());
        order.setCity(checkoutForm.getCity());
        order.setState(checkoutForm.getState());
        order.setPostalCode(checkoutForm.getPostalCode());
        order.setPaymentMethod(checkoutForm.getPaymentMethod());
        order.setStatus(OrderStatus.PLACED);

        BigDecimal total = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItem.getProduct().getId());
            orderItem.setProductName(cartItem.getProduct().getName());
            orderItem.setProductImageUrl(cartItem.getProduct().getImageUrl());
            orderItem.setUnitPrice(cartItem.getProduct().getPriceInr());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSubtotal(cartItem.getSubtotal());
            order.addItem(orderItem);

            total = total.add(cartItem.getSubtotal());

            var product = cartItem.getProduct();
            product.setStockQuantity(Math.max(0, product.getStockQuantity() - cartItem.getQuantity()));
            productRepository.save(product);
        }

        order.setTotalAmount(total);
        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(session);
        return savedOrder;
    }
}