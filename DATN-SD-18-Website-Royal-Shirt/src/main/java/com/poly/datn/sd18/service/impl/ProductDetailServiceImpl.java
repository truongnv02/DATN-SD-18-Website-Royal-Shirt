package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.ProductDetail;
import com.poly.datn.sd18.repository.ProductDetailRepository;
import com.poly.datn.sd18.service.ProductDetailService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {
    private final ProductDetailRepository productDetailRepository;

    public int countProduct(int number) {
        return productDetailRepository.countProduct(number);
    }

    public List<ProductDetail> listProduct(int number) {
        return productDetailRepository.listProduct(number);
    }
}
