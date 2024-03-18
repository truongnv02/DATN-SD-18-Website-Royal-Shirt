package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.OrderDetail;
import com.poly.datn.sd18.model.dto.OrderDetailDTO;

public interface OrderDetailService {
    OrderDetail addOrderDetail(OrderDetailDTO orderDetailDTO);
}
