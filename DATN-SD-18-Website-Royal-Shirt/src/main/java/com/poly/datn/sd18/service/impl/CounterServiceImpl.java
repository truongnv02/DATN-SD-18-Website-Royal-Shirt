package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.dto.response.ProductDetailCounterResponse;
import com.poly.datn.sd18.repository.CounterRepository;
import com.poly.datn.sd18.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {
    @Autowired
    CounterRepository counterRepository;

    @Override
    public List<ProductDetailCounterResponse> getListProductDetailCounter() {
        return counterRepository.getListProductDetailCounter();
    }
}
