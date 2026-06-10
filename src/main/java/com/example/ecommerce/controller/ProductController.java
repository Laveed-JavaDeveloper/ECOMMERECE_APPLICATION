package com.example.ecommerce.controller;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id)
            .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        List<String> sizes = product.getAvailableSizes() == null || product.getAvailableSizes().isBlank()
            ? List.of("S", "M", "L", "XL")
            : Arrays.stream(product.getAvailableSizes().split(","))
                .map(String::trim)
                .filter(size -> !size.isBlank())
                .toList();
        model.addAttribute("product", product);
        model.addAttribute("sizes", sizes);
        model.addAttribute("categories", Category.values());
        return "product-details";
    }
}
