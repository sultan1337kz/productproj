package com.example.productproj.service;

import com.example.productproj.entity.Product;
import com.example.productproj.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;


    @Test
    public void whenSaveProduct_shouldReturnProduct() {
        Product product = new Product();
        product.setName("Phone");
        product.setQuantity(10L);

        when(productRepository.save(product)).thenReturn(product);

        Product created = productService.save(product);

        assertEquals(created.getName(), product.getName());
        verify(productRepository).save(product);
    }

    @Test
    public void shouldReturnAllProducts() {
        List<Product> products = new ArrayList();
        products.add(new Product());

        given(productRepository.findAll()).willReturn(products);

        List<Product> expected = productService.findAll();

        assertEquals(expected, products);
        verify(productRepository).findAll();
    }

    @Test
    public void whenGivenId_shouldDeleteProduct_ifFound(){
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Test Name");
        product.setQuantity(1L);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        productService.deleteById(product.getId());
        verify(productRepository).deleteById(product.getId());
    }
}
