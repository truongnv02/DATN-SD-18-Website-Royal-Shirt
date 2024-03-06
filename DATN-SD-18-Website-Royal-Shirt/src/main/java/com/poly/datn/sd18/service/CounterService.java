package com.poly.datn.sd18.service;

import com.poly.datn.sd18.dto.response.ProductDetailCounterResponse;

import java.util.List;

public interface CounterService {
    List<ProductDetailCounterResponse> getListProductDetailCounter();
}
