package com.example.OrderService.Service;

import com.example.OrderService.Client.ProductClient;
import com.example.OrderService.DTO.OrderRequestDto;
import com.example.OrderService.DTO.OrderResponseDto;
import com.example.OrderService.DTO.ProductResponseDto;
import com.example.OrderService.Exception.QuantityNotAvailable;
import com.example.OrderService.Model.Orders;
import com.example.OrderService.Producer.OrderProducer;
import com.example.OrderService.Repository.OrderRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.OrderService.DTO.OrderCreatedEvent;

@Service
public class OrderServiceImpl implements OrderService{

   @Autowired
   private OrderRepo orderRepo;

   @Autowired
   private ProductClient productClient;

    @Autowired
    private OrderProducer orderProducer;

    @CircuitBreaker(
            name = "productService",
            fallbackMethod = "fallbackProduct")
    @Override
//    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto){
//        ProductResponseDto product = productClient.getProductById(orderRequestDto.getProductId());
//        if(product.getStock() < orderRequestDto.getQuantity()){
//            throw new QuantityNotAvailable("Requested quantity not available in stock");
//        }
//        productClient.reduceStock(orderRequestDto.getProductId(), orderRequestDto.getQuantity());
//        Orders order = new Orders();
//        order.setProductId(orderRequestDto.getProductId());
//        order.setQuantity(orderRequestDto.getQuantity());
//        order.setStatus("CREATED");
//        Orders savedOrder = orderRepo.save(order);
//        return new OrderResponseDto(order.getOrderId(),
//                savedOrder.getProductId(),
//                savedOrder.getQuantity(),
//                savedOrder.getStatus());
//    }

    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto){
//        ProductResponseDto product = productClient.getProductById(orderRequestDto.getProductId());
//        if(product.getStock() < orderRequestDto.getQuantity()){
//            throw new QuantityNotAvailable("Requested quantity not available in stock");
//        }
//        productClient.reduceStock(orderRequestDto.getProductId(), orderRequestDto.getQuantity());
        Orders order = new Orders();
        order.setProductId(orderRequestDto.getProductId());
        order.setQuantity(orderRequestDto.getQuantity());
        order.setStatus("CREATED");
        Orders savedOrder = orderRepo.save(order);
        OrderCreatedEvent event =
                new OrderCreatedEvent(
                        savedOrder.getProductId(),
                        savedOrder.getQuantity());

        System.out.println("Before sending event");
        orderProducer.sendOrderCreatedEvent(event);
        return new OrderResponseDto(order.getOrderId(),
                savedOrder.getProductId(),
                savedOrder.getQuantity(),
                savedOrder.getStatus());

    }


//    public OrderResponseDto fallbackProduct(
//            OrderRequestDto request,
//            Exception ex) {
//
//        System.out.println("Fallback called");
//        System.out.println(ex.getMessage());
//
//        throw new RuntimeException(
//                "Product Service Unavailable");
//    }

    public OrderResponseDto fallbackProduct(
            OrderRequestDto request,
            Exception ex) {

        System.out.println("Fallback called");
        System.out.println(ex.getMessage());

        return new OrderResponseDto(
                null,
                request.getProductId(),
                request.getQuantity(),
                "PRODUCT SERVICE DOWN"
        );
    }
}


