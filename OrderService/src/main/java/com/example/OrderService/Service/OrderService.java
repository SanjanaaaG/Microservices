package com.example.OrderService.Service;

import com.example.OrderService.DTO.OrderRequestDto;
import com.example.OrderService.DTO.OrderResponseDto;

public interface OrderService {
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
}
