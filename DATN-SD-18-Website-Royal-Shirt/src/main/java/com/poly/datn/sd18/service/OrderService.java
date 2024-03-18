package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.model.dto.OrderDTO;

public interface OrderService {
    Order addOrder(OrderDTO orderDTO);
}
