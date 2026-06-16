package com.example.OrderService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponseDto {
    private Long orderId;
    private Long productId;
    private Long quantity;
    private String status;
}
