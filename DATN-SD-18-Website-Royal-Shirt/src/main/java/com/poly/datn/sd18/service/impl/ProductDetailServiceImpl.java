package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.repository.ProductDetailRepository;
import com.poly.datn.sd18.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {
    private final ProductDetailRepository productDetailRepository;
}
