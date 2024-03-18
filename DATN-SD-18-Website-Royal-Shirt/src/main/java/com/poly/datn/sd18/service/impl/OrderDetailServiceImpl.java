package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.OrderDetail;
import com.poly.datn.sd18.model.dto.OrderDetailDTO;
import com.poly.datn.sd18.repository.OrderDetailRepository;
import com.poly.datn.sd18.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail addOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = orderDetailDTO.map(new OrderDetail());
        return orderDetailRepository.save(orderDetail);
    }
}
