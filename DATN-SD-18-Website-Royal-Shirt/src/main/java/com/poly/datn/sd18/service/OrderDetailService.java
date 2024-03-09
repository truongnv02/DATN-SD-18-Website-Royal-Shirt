package com.poly.datn.sd18.service;

import com.poly.datn.sd18.dto.request.OrderDetailCounterRequest;
import com.poly.datn.sd18.entity.OrderDetail;

public interface OrderDetailService {
    OrderDetail add(OrderDetailCounterRequest orderDetailCounterRequest);
}
