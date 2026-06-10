package com.example.ecommerce.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.ecommerce.repository.ProductRepository;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void homePageRenders() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("index"))
            .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("Men's Fashion Store")));
    }

    @Test
    void productDetailPageRenders() throws Exception {
        Long productId = productRepository.findAll().getFirst().getId();
        String productName = productRepository.findAll().getFirst().getName();

        mockMvc.perform(get("/products/" + productId))
            .andExpect(status().isOk())
            .andExpect(view().name("product-details"))
            .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains(productName)));
    }

    @Test
    void cartPageRenders() throws Exception {
        mockMvc.perform(get("/cart"))
            .andExpect(status().isOk())
            .andExpect(view().name("cart"))
            .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("Your Cart")));
    }
}