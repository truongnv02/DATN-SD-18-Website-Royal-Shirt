package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {

    @Query("SELECT pro_details\n" +
            "FROM ProductDetail pro_details\n" +
            "WHERE pro_details.product.name LIKE CONCAT('%', :condition, '%')")
    List<ProductDetail> getProductDetailByCondition(String condition);
}
