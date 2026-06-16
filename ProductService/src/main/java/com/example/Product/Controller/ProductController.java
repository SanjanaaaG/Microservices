package com.example.Product.Controller;

import com.example.Product.DTO.ProductRequest;
import com.example.Product.DTO.ProductResponse;
import com.example.Product.Model.Product;
import com.example.Product.Service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("/api/products")
    public ResponseEntity<ProductResponse> addProduct(@Valid  @RequestBody ProductRequest product){
        try{
            ProductResponse p = productService.addProduct(product);
            if(p!=null){
                return new ResponseEntity<>(p,HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        try{
            List<Product> p = productService.getAllProducts();
            if(p != null) {
                return new ResponseEntity<>(p, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/products/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId){
        try{
            ProductResponse p = productService.getProductById(productId);
            if(p!=null){
                return new ResponseEntity<>(p,HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
    }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/products/update/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product , @PathVariable Long productId) {
        try {
            Product p = productService.updateProduct(product, productId);
            if (p != null) {
                return new ResponseEntity<>(p, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/products/{productId}/reduce-stock")
    public ResponseEntity<Void> reduceStock(@PathVariable Long productId,
                                            @RequestParam Long quantity) {
        try {
            productService.updateStock(productId,quantity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
