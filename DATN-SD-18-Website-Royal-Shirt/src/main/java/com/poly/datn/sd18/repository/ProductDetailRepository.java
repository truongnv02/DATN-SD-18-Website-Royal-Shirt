package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.Product;
import com.poly.datn.sd18.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    @Query("select c from ProductDetail c where c.product.id =:productId")
    List<ProductDetail> getAllProductDetailByProductId(@Param("productId") Integer productId);
}
