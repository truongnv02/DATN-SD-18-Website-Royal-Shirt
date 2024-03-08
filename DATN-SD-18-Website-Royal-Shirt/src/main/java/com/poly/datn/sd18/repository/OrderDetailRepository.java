package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findAllByOrder(Order order);
}
