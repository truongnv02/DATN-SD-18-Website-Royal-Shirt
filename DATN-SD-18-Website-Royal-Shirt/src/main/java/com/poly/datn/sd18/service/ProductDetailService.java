package com.poly.datn.sd18.service;

import com.poly.datn.sd18.dto.ProductDetailRequest;
import com.poly.datn.sd18.entity.ProductDetail;
import com.poly.datn.sd18.exceptions.DataNotFoundException;

import java.util.List;

public interface ProductDetailService {
    ProductDetail add(ProductDetailRequest productDetailRequest);
    ProductDetail getProductDetail(Integer product) throws DataNotFoundException;
    List<ProductDetail> getProductDetails();
    List<ProductDetail> getProductDetailsByConditions(String condition);
}
