package com.example.productproj.service;

import com.example.productproj.entity.Product;
import com.example.productproj.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product save(Product product) {
        product.setId(UUID.randomUUID());
        return productRepository.save(product);
    }

    @Override
    public void deleteById(UUID productId) {

        Optional<Product> product = findById(productId);

        if (!product.isPresent()) {
            throw new RuntimeException("Product id not found - " + productId);
        }

        productRepository.deleteById(productId);
    }

    @Override
    public void addQuantityById(UUID productId) {
        lockProduct(productId);
        Optional<Product> product = findById(productId);

        if (!product.isPresent()) {
            throw new RuntimeException("Product id not found - " + productId);
        }

        long q = product.get().getQuantity();

        productRepository.modifyQuantityById(productId, q + 1L);

    }

    @Override
    public void deductQuantityById(UUID productId) {
        lockProduct(productId);
        Optional<Product> product = findById(productId);

        if (!product.isPresent()) {
            throw new RuntimeException("Product id not found - " + productId);
        }

        long q = product.get().getQuantity();
        if (q == 0) {
            throw new RuntimeException("Product is 0");
        }

        productRepository.modifyQuantityById(productId, q - 1L);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void lockProduct(UUID productId) {
        productRepository.selectForUpdate(productId);
    }


}






