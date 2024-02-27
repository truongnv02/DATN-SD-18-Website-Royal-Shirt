package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.dto.ProductDetailRequest;
import com.poly.datn.sd18.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/rest/product-detail")
public class ProductDetailRestController {
    @Autowired
    ProductDetailService productDetailService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ProductDetailRequest productDetailRequest) {
        return ResponseEntity.ok(productDetailService.add(productDetailRequest));
    }
}
