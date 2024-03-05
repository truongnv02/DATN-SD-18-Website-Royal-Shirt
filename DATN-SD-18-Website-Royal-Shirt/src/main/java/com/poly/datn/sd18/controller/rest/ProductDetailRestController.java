package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.dto.ProductDetailRequest;
import com.poly.datn.sd18.dto.ProductResponse;
import com.poly.datn.sd18.entity.ProductDetail;
import com.poly.datn.sd18.exceptions.DataNotFoundException;
import com.poly.datn.sd18.service.ProductDetailService;
import com.poly.datn.sd18.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/rest/product-detail")
@AllArgsConstructor
public class ProductDetailRestController {
    ProductDetailService productDetailService;

    @Autowired
    ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductDetail>> getAllProductDetails() {
        return ResponseEntity.ok().body(productDetailService.getProductDetails());
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ProductDetailRequest productDetailRequest) {
        return ResponseEntity.ok(productDetailService.add(productDetailRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductDetail(@PathVariable("id") Integer productDetailId) {
        try {
            ProductDetail productDetail = productDetailService.getProductDetail(productDetailId);
            return ResponseEntity.ok(productDetail);
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm");
        }
    }
    @GetMapping("1")
    public ResponseEntity<List<ProductResponse>> getAllProductDetails1() {
        return ResponseEntity.ok().body(productService.getAll());
    }


}
