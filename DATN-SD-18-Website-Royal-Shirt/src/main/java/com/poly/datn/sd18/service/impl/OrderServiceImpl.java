package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Customer;
import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.model.dto.OrderDTO;
import com.poly.datn.sd18.repository.OrderRepository;
import com.poly.datn.sd18.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order addOrder(OrderDTO orderDTO) {
        Order order = orderDTO.map(new Order());
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrderByCustomerId(Integer customerId) {
        return orderRepository.findOrderByCustomerId(customerId);
    }
}
