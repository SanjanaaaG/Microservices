package com.example.Product.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String productName;
    @Positive(message = "Price must be greater than zero")
    private Long price;
    @Positive(message = "Stock must be greater than zero")
    private Long stock;

}
