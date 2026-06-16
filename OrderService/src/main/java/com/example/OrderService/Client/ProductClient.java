package com.example.OrderService.Client;

import com.example.OrderService.DTO.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Component
//public class ProductClient {

//    @Autowired
//    private  RestTemplate restTemplate;

//    private final RestTemplate restTemplate;

//    public ProductClient(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public ProductResponseDto getProductById(Long id){
//        String url = "http://localhost:8081/api/products/" + id;
//        return restTemplate.getForObject(
//                url,
//                ProductResponseDto.class
//        );
//    }

//    public void reduceStock(Long productId, Long quantity){
//        String url = "http://localhost:8081/api/products/"+productId+"/reduce-stock?quantity="+quantity;
//        restTemplate.put(url, null);
//    }
//}

    @FeignClient(name = "PRODUCT-SERVICE")
    public interface ProductClient {

        @GetMapping("/api/products/{id}")
        ProductResponseDto getProductById(
                @PathVariable Long id);

        @PutMapping("/api/products/{id}/reduce-stock")
        void reduceStock(
                @PathVariable Long id,
                @RequestParam Long quantity);
    }

