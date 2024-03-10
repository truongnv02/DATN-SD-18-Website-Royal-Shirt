package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.dto.request.OrderCounterRequest;
import com.poly.datn.sd18.dto.request.OrderDetailCounterRequest;
import com.poly.datn.sd18.dto.response.ProductDetailCounterResponse;
import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.entity.OrderDetail;
import com.poly.datn.sd18.entity.ProductDetail;
import com.poly.datn.sd18.repository.CounterRepository;
import com.poly.datn.sd18.repository.OrderDetailRepository;
import com.poly.datn.sd18.repository.OrderRepository;
import com.poly.datn.sd18.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {
    @Autowired
    CounterRepository counterRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<ProductDetailCounterResponse> getListProductDetailCounter() {
        return counterRepository.getListProductDetailCounter();
    }

    @Override
    public ProductDetail checkQuantity(Integer idProductDetail, Integer quantity) {
        return counterRepository.checkQuantity(idProductDetail,quantity);
    }

    @Override
    public Order addOrder(OrderCounterRequest orderCounterRequest) {
        Order order = orderCounterRequest.map(new Order());
        return orderRepository.save(order);
    }

    @Override
    public OrderDetail addOrderDetail(OrderDetailCounterRequest orderDetailCounterRequest) {
        OrderDetail orderDetail = orderDetailCounterRequest.map(new OrderDetail());
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void updateQuantity(Integer idProductDetail, Integer quantity) {
        counterRepository.updateQuantity(idProductDetail,quantity);
    }


}
