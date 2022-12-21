package com.example.trabajofinalpettinaroli.services;


import com.example.trabajofinalpettinaroli.entities.Product;
import com.example.trabajofinalpettinaroli.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Create Product Method:
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Find Product By Id method:
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    // Method to find Product by ProductCode
    public Optional<Product> findProductByPrdCode(Integer prdCode) {
        return productRepository.findProductByProductCode(prdCode);
    }

    // Find All Products Method
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    // Delete Product By Id
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
