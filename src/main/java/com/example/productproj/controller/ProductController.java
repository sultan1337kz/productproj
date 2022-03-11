package com.example.productproj.controller;

import com.example.productproj.entity.Product;
import com.example.productproj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public List<Product> findAll() {
		return productService.findAll();
	}

	@GetMapping("/products/{productId}")
	public Optional<Product> getProduct(@PathVariable UUID productId) {

		Optional<Product> product = productService.findById(productId);
		
		if (!product.isPresent()) {
			throw new RuntimeException("Product id not found - " + product);
		}
		
		return product;
	}

	
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		
		productService.save(product);
		
		return product;
	}

	
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product product) {

		productService.save(product);
		
		return product;
	}
	

	@DeleteMapping("/products/{productId}")
	public String deleteProduct(@PathVariable UUID productId) {
		
		productService.deleteById(productId);
		
		return "Deleted Product id - " + productId;
	}


	@PatchMapping("/products/{productId}/add")
	public String addProductQuantity(@PathVariable UUID productId) {

		productService.addQuantityById(productId);

		return "Added Product id - " + productId;
	}

	@PatchMapping("/products/{productId}/deduct")
	public String deductProductQuantity(@PathVariable UUID productId) {

		productService.deductQuantityById(productId);

		return "Deducted Product id - " + productId;
	}

}










