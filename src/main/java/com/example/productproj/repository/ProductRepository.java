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
    @Query(value = "update product set quantity = :quantity where id = :id",
            nativeQuery = true)
    void modifyQuantityById(@Param("id") UUID id, @Param("quantity") long quantity);

    @Query(value = "SELECT * FROM product where id = :id FOR UPDATE ",
            nativeQuery = true)
    Optional<Product> selectForUpdate(@Param("id") UUID id);
}