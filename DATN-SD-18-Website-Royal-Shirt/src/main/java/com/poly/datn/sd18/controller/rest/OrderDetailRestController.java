package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.entity.Customer;
import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.entity.OrderDetail;
import com.poly.datn.sd18.model.dto.OrderDetailDTO;
import com.poly.datn.sd18.service.OrderDetailService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-detail")
public class OrderDetailRestController {
    private final OrderDetailService orderDetailService;
    private final HttpSession session;

    @PostMapping("/add")
    public ResponseEntity<?> addOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO){
        return ResponseEntity.ok(orderDetailService.addOrderDetail(orderDetailDTO));
    }
}
