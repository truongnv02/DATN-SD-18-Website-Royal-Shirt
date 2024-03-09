package com.poly.datn.sd18.service;

import com.poly.datn.sd18.dto.response.ProductDetailCounterResponse;
import com.poly.datn.sd18.entity.ProductDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CounterService {
    List<ProductDetailCounterResponse> getListProductDetailCounter();
    ProductDetail updateQuantity(Integer quantity,Integer idProductDetail);

}
