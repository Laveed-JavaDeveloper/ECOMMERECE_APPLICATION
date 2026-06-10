package com.example.ecommerce.controller;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.OrderProcessingService;
import com.example.ecommerce.web.dto.CheckoutForm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartService cartService;
    private final OrderProcessingService orderProcessingService;

    public CheckoutController(CartService cartService, OrderProcessingService orderProcessingService) {
        this.cartService = cartService;
        this.orderProcessingService = orderProcessingService;
    }

    @GetMapping
    public String checkoutPage(Model model, HttpSession session) {
        model.addAttribute("checkoutForm", new CheckoutForm());
        model.addAttribute("cartItems", cartService.getCartItems(session));
        model.addAttribute("cartTotal", cartService.getCartTotal(session));
        return "checkout";
    }

    @PostMapping
    public String placeOrder(@Valid @ModelAttribute CheckoutForm checkoutForm,
                             BindingResult bindingResult,
                             Model model,
                             HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cartItems", cartService.getCartItems(session));
            model.addAttribute("cartTotal", cartService.getCartTotal(session));
            return "checkout";
        }

        Order order = orderProcessingService.placeOrder(checkoutForm, session);
        model.addAttribute("order", order);
        return "order-confirmation";
    }
}
