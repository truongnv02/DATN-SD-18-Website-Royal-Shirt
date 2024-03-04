package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Product;
import com.poly.datn.sd18.model.response.ProductResponse;
import com.poly.datn.sd18.repository.ProductRepository;
import com.poly.datn.sd18.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProductDetails() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Product> pageProducts(Integer pageNo) {
        Pageable page = PageRequest.of(pageNo - 1, 9);
        return productRepository.findAll(page);
    }

    @Override
    public List<Product> searchProductName(String name) {
        return productRepository.searchProductByName(name);
    }

    @Override
    public Page<Product> searchProducts(String keyWord, Integer pageNo) {
        List<Product> list = searchProductName(keyWord);
        System.out.println("day ne: " + list);
        Pageable pageable = PageRequest.of(pageNo - 1, 1);
        Integer start = (int) pageable.getOffset();
        Integer end = (start + pageable.getPageSize()) > list.size() ? list.size() : start + pageable.getPageSize();
        list = list.subList(start, end);
        return new PageImpl<>(list, pageable, searchProductName(keyWord).size());
    }

    @Override
    public Page<ProductResponse> pageProductResponse(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 9);
        return null;
    }
}
