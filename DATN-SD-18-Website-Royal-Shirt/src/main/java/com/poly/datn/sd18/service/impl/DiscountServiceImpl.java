package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.repository.DiscountRepository;
import com.poly.datn.sd18.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
}
