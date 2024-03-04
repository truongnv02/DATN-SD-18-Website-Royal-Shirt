package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Product;
import com.poly.datn.sd18.entity.ProductDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetail> getAllProductDetail();
    ProductDetail getProductDetailByProductId(Integer productId);
}
