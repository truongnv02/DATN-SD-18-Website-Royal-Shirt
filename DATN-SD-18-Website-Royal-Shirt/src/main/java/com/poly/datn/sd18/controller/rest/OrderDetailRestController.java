package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.model.dto.OrderDetailDTO;
import com.poly.datn.sd18.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-detail")
public class OrderDetailRestController {
    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping("/add")
    public ResponseEntity<?> addOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO){
        return ResponseEntity.ok(orderDetailService.addOrderDetail(orderDetailDTO));
    }
}
