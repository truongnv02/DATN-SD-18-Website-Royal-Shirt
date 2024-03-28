package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = """
                SELECT *
                FROM orders
                WHERE
                    customer_id = :customerId
                ORDER BY id DESC;
            """, nativeQuery = true)
    List<Order> findOrderByCustomerId(@Param("customerId") Integer customerId);
}
