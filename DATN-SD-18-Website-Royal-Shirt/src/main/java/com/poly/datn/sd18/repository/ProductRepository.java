package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select c from Product c where c.name like %:name% and c.status = 0")
    List<Product> searchProductByName(@Param("name") String name);

//    @Query("SELECT p FROM Product p WHERE " +
//            "(:brandId IS NULL OR p.brand.id = :brandId) AND " +
//            "(:categoryId IS NULL OR p.category.id = :categoryId) AND " +
//            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
//            "(:maxPrice IS NULL OR p.price <= :maxPrice)")
//    Page<Product> findByCriteria(@Param("brandId") Integer brandId,
//                                       @Param("categoryId") Integer categoryId,
//                                       @Param("minPrice") BigDecimal minPrice,
//                                       @Param("maxPrice") BigDecimal maxPrice,
//                                       Pageable pageable);

//    Page<ProductResponse> pageProductResponse(Pageable pageable);
}
