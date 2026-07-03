package com.example.Product.Service;

import com.example.Product.DTO.ProductRequest;
import com.example.Product.DTO.ProductResponse;
import com.example.Product.Exception.ProductNotFoundException;
import com.example.Product.Model.Product;
import com.example.Product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepo;

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product pro =  productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found") );
        return new ProductResponse(
                pro.getProductId(),
                pro.getProductName(),
                pro.getPrice(),
                pro.getStock());
    }

    @Override
    public ProductResponse addProduct(ProductRequest product) {
        Product p = new Product();
        p.setProductName(product.getProductName());
        p.setPrice(product.getPrice());
        p.setStock(product.getStock());
        Product savedProduct = productRepo.save(p);
        return new ProductResponse(savedProduct.getProductId(),
                                   savedProduct.getProductName(),
                                   savedProduct.getPrice(),
                                   savedProduct.getStock());
    }

    @Override
    public Product updateProduct(Product product , Long id) {
        productRepo.findById(id).get();
        return productRepo.save(product);
    }

    @Override
    public void reduceStock(Long productId, Long quantity) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found") );
        if(product.getStock() < quantity){
            throw new RuntimeException("Insuffecient stock");
        }
        product.setStock(product.getStock() - quantity);
        productRepo.save(product);
    }


}
