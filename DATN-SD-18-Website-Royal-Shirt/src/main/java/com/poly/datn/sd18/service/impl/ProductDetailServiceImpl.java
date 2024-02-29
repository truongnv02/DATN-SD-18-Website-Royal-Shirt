package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.dto.ProductDetailRequest;
import com.poly.datn.sd18.entity.ProductDetail;
import com.poly.datn.sd18.exceptions.DataNotFoundException;
import com.poly.datn.sd18.repository.ProductDetailRepository;
import com.poly.datn.sd18.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ProductDetail getProductDetail(Integer product) throws DataNotFoundException {
        return productDetailRepository.findById(product)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy dữ liệu của sản phẩm có mã id là: " + product));
    }

    @Override
    public List<ProductDetail> getProductDetails() {
        return productDetailRepository.findAll();
    }

    @Override
    public List<ProductDetail> getProductDetailsByConditions(String condition) {
        return productDetailRepository.getProductDetailByCondition(condition);
    }
}
