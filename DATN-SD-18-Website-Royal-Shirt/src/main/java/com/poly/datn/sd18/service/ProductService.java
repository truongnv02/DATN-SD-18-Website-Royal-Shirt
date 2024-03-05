package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Product;
import com.poly.datn.sd18.model.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> getAllProductDetails();
    Product findProductById(Integer id);
    Page<Product> pageProducts(Integer pageNo);
    List<Product> searchProductName(String name);
    Page<Product> searchProducts(String keyWord, Integer pageNo);
    Page<ProductResponse> pageProductResponse(Pageable pageable);
}
