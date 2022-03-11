package com.example.productproj.repository;

import com.example.productproj.entity.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, UUID> {

    @Modifying
    @Query("update product set quantity = :quantity where id = :id")
    void modifyQuantityById(@Param("id") UUID id, @Param("quantity") long quantity);

    @Query("SELECT id FROM product where id = :id FOR UPDATE")
    Optional<UUID> selectForUpdate(@Param("paymentId") UUID id);
}