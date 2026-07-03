package com.example.Product.Consumer;

import com.example.Product.DTO.OrderCreatedEvent;
import com.example.Product.Service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @Autowired
    ProductServiceImpl productService;

    @KafkaListener(
            topics = "order-created",
            groupId = "product-group")
    public void consume(OrderCreatedEvent event){

        productService.reduceStock(
                event.getProductId(),
                event.getQuantity());

        System.out.println("**************");
        System.out.println("Message Received");
        System.out.println(event.getProductId());
        System.out.println(event.getQuantity());
        System.out.println("**************");

        System.out.println(
                "Product Id : " + event.getProductId());

        System.out.println(
                "Quantity : " + event.getQuantity());

    }

}
