package com.example.Product.Service;

import com.example.Product.DTO.ProductRequest;
import com.example.Product.DTO.ProductResponse;
import com.example.Product.Model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public ProductResponse getProductById(Long id);
    public ProductResponse addProduct(ProductRequest product);
    public Product updateProduct(Product product, Long id);
    public void updateStock(Long productId , Long quantity);
}
