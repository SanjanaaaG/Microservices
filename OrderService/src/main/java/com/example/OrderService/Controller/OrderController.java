package com.example.OrderService.Controller;

import com.example.OrderService.DTO.OrderRequestDto;
import com.example.OrderService.DTO.OrderResponseDto;
import com.example.OrderService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/api/orders")
    public ResponseEntity<OrderResponseDto>createOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderResponseDto o = orderService.createOrder(orderRequestDto);
        if(o!=null){
            return ResponseEntity.ok(o);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

}
