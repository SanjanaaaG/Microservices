package com.example.OrderService.Producer;

import com.example.OrderService.DTO.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

        @Autowired
        private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

        public void sendOrderCreatedEvent(
                OrderCreatedEvent event){

            System.out.println("Order Created Event Sent to Kafka");
            System.out.println("Product Id : " + event.getProductId());
            System.out.println("Quantity : " + event.getQuantity());

            kafkaTemplate.send(
                    "order-created",
                    event);
        }
}
