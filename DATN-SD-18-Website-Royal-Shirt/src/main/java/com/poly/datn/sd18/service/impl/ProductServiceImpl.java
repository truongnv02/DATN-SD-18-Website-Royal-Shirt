package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.entity.Product;
import com.poly.datn.sd18.repository.ProductRepository;
import com.poly.datn.sd18.service.ProductService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public int countOrder() {
        return productRepository.countOrder();
    }

    @Transactional
    public List<Object> listHotSelling(int num) {
        return productRepository.hotSelling(num);
    }

    @Override
    public List<Product> getListProduct() {
        return productRepository.getListProduct();
    }
}
