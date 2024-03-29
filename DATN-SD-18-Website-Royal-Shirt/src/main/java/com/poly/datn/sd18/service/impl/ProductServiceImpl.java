package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.repository.ProductRepository;
import com.poly.datn.sd18.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
}
