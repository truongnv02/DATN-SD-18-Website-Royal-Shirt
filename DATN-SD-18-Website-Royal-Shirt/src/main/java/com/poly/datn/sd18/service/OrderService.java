package com.poly.datn.sd18.service;

import com.poly.datn.sd18.dto.request.OrderCounterRequest;
import com.poly.datn.sd18.entity.Order;

public interface OrderService {
    Order add(OrderCounterRequest orderCounterRequest);
}
