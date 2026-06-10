package com.example.ecommerce.controller;

import com.example.ecommerce.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String viewCart(Model model, HttpSession session) {
        model.addAttribute("cartItems", cartService.getCartItems(session));
        model.addAttribute("cartTotal", cartService.getCartTotal(session));
        return "cart";
    }

    @PostMapping("/add/{productId}")
    public String addItem(@PathVariable Long productId,
                          @RequestParam(defaultValue = "1") int quantity,
                          HttpSession session) {
        cartService.addProduct(session, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/update/{productId}")
    public String updateItem(@PathVariable Long productId,
                             @RequestParam int quantity,
                             HttpSession session) {
        cartService.updateQuantity(session, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeItem(@PathVariable Long productId, HttpSession session) {
        cartService.removeProduct(session, productId);
        return "redirect:/cart";
    }
}
