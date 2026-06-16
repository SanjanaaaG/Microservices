package com.example.OrderService.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Orders {

    @GeneratedValue
    @Id
    private Long orderId;
    private Long productId;
    private Long quantity;
    private String status;

}
