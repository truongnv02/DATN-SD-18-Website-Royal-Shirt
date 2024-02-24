package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.dto.ProductDetailRequest;
import com.poly.datn.sd18.entity.ProductDetail;
import com.poly.datn.sd18.repository.ProductDetailRepository;
import com.poly.datn.sd18.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Override
    public ProductDetail add(ProductDetailRequest productDetailRequest) {
        ProductDetail productDetail = productDetailRequest.map(new ProductDetail());
        return productDetailRepository.save(productDetail);
    }
}
