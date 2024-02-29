package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.dto.ProductDetailRequest;
import com.poly.datn.sd18.entity.ProductDetail;
import com.poly.datn.sd18.exceptions.DataNotFoundException;
import com.poly.datn.sd18.service.ProductDetailService;
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


}
