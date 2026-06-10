package com.example.ecommerce.controller.api;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductApiController {

    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) Category category) {
        return category == null ? productService.getFeaturedProducts() : productService.getProductsByCategory(category);
    }
}
