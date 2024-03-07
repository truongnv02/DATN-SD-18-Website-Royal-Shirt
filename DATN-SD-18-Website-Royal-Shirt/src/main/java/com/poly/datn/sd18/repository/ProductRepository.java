package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("Select Count(p.id) From Product p")
    int countOrder();

    @Procedure(name = "hotSelling")
    List<Object> hotSelling(@Param("minQuantity") int minQuantity);
}
