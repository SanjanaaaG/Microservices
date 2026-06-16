package com.example.OrderService.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NonNull;

@Data
public class OrderRequestDto {

    @NotNull
    private Long productId;

    @Positive
    private Long quantity;



}
