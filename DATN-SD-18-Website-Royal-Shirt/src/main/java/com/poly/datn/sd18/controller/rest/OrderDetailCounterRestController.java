package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.dto.request.OrderCounterRequest;
import com.poly.datn.sd18.dto.request.OrderDetailCounterRequest;
import com.poly.datn.sd18.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/rest/orderDetailCounter")
public class OrderDetailCounterRestController {
    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody OrderDetailCounterRequest orderDetailCounterRequest) {
        return ResponseEntity.ok(orderDetailService.add(orderDetailCounterRequest));
    }
}
