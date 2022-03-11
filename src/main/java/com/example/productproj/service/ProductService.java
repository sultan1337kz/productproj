package com.example.productproj.service;

import com.example.productproj.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

	public List<Product> findAll();
	
	public Optional<Product> findById(UUID productId);
	
	public Product save(Product product);
	
	public void deleteById(UUID productId);

	public void addQuantityById(UUID productId);

	public void deductQuantityById(UUID productId);

	public void lockProduct(UUID productId);
	
}
