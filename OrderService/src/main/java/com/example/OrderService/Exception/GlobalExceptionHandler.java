package com.example.OrderService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductUnavailable.class)
    public ResponseEntity<String> handleProductUnavailable(ProductUnavailable ex){
        return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(ex.getMessage());
    }

    @ExceptionHandler(QuantityNotAvailable.class)
    public ResponseEntity<String> handleQuantityNotAvailable(QuantityNotAvailable ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

}
