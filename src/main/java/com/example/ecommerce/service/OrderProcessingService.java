package com.example.ecommerce.service;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.web.dto.CheckoutForm;
import jakarta.servlet.http.HttpSession;

public interface OrderProcessingService {
    Order placeOrder(CheckoutForm checkoutForm, HttpSession session);
}