package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.repository.CartDetailRepository;
import com.poly.datn.sd18.service.CartDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartDetailServiceImpl implements CartDetailService {
    private final CartDetailRepository cartDetailRepository;
}
