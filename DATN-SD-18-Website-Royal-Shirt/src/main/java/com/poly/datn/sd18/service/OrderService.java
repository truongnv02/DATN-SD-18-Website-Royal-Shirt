package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.entity.OrderDetail;
import com.poly.datn.sd18.model.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    Order addOrder(OrderDTO orderDTO);
    List<Order> findOrderByCustomerId(Integer customerId);

}
