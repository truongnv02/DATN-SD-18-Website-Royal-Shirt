package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.exceptions.DataNotFoundException;
import com.poly.datn.sd18.requests.OrderCounterRequest;
import com.poly.datn.sd18.responses.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderCounterRequest orderCounterRequest) throws DataNotFoundException;

    OrderResponse getBill(Integer orderId) throws DataNotFoundException;

}
