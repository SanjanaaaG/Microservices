package com.example.OrderService.Exception;

public class QuantityNotAvailable extends RuntimeException{
    public QuantityNotAvailable(String Message) {
        super(Message);
    }
}
