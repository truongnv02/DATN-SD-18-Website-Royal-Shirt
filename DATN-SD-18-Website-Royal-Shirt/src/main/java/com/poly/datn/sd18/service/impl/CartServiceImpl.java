package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.repository.CartRepository;
import com.poly.datn.sd18.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
}
