package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.ProductDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {

    @Query("Select Count(p.id) From ProductDetail p Where p.quantity < :number")
    int countProduct(int number);

    @Query("Select p From ProductDetail p Where p.quantity < :number")
    List<ProductDetail> listProduct(int number);

    @Query("Select p From ProductDetail p")
    List<ProductDetail> getListProduct();
}
