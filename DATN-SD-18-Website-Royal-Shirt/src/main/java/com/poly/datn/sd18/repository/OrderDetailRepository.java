package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.OrderDetail;
import com.poly.datn.sd18.model.response.OrderDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = """
            SELECT *
            FROM
                order_details
            WHERE
                order_id = :orderId
            """, nativeQuery = true)
    List<OrderDetailResponse> findOrderDetailByOrderId(@Param("orderId") Integer id);
}
