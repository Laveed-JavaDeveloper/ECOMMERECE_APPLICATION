package com.example.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.service.ProductService;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/", "/inventory"})
    public String index(Model model) {
        model.addAttribute("products", productService.getFeaturedProducts());
        model.addAttribute("categories", Category.values());
        return "index";
    }
}
