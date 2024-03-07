package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.OrderDetail;
import com.poly.datn.sd18.entity.Product;
import com.poly.datn.sd18.entity.ProductDetail;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
