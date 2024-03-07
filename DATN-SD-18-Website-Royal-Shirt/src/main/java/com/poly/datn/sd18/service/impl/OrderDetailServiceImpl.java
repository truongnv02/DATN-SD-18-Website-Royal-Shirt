package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.OrderDetail;
import com.poly.datn.sd18.repository.OrderDetailRepository;
import com.poly.datn.sd18.service.OrderDetailService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
}
