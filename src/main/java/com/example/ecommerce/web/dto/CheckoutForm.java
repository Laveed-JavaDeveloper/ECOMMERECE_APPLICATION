package com.example.ecommerce.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutForm {

    @NotBlank
    private String customerName;

    @NotBlank
    private String mobileNumber;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String shippingAddress;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String paymentMethod;
}