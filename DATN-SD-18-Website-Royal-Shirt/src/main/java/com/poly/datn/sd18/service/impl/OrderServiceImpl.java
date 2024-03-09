package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.dto.request.OrderCounterRequest;
import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.repository.OrderRepository;
import com.poly.datn.sd18.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order add(OrderCounterRequest orderCounterRequest) {
        Order order = orderCounterRequest.map(new Order());
        return orderRepository.save(order);
    }
}
