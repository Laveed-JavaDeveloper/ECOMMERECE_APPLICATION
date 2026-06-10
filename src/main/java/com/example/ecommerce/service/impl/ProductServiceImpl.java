package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getFeaturedProducts() {
        return productRepository.findAllByOrderByCategoryAscNameAsc();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllByOrderByCategoryAscNameAsc();
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}