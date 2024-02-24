package com.poly.datn.sd18.service;

import com.poly.datn.sd18.dto.ProductDetailRequest;
import com.poly.datn.sd18.entity.ProductDetail;

public interface ProductDetailService {
    ProductDetail add(ProductDetailRequest productDetailRequest);
}
