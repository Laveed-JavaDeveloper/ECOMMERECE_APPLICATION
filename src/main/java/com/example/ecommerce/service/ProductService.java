package com.example.ecommerce.service;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getFeaturedProducts();

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(Category category);

    Optional<Product> getProductById(Long id);
}