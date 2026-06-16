package com.example.OrderService.Exception;

public class ProductUnavailable extends Exception {
    public ProductUnavailable(String message){
        super(message);
    }
}
