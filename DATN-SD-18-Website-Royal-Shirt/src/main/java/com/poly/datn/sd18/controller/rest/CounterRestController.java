package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.entity.Customer;
import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.exceptions.DataNotFoundException;
import com.poly.datn.sd18.requests.OrderCounterRequest;
import com.poly.datn.sd18.service.CustomerService;
import com.poly.datn.sd18.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/counter")
@RequiredArgsConstructor
public class CounterRestController {


    private final CustomerService customerService;
    private final OrderService orderService;

    @PostMapping("/add-bill")
    public ResponseEntity<?> addBill() {
        return null;
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        return null;
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkOut(@RequestBody OrderCounterRequest orderCounterRequest) {
        try {
            System.out.println("Handle checkout");
            System.out.println(orderCounterRequest.toString());
            return ResponseEntity.ok(orderService.createOrder(orderCounterRequest));
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body("Something wrong when save bill");
        }
    }

    @GetMapping("/bill/{id}")
    public ResponseEntity<?> getBill(@PathVariable("id") Integer orderId) {
        try {
            return ResponseEntity.ok(orderService.getBill(orderId));
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body("Something wrong!");
        }
    }

    @GetMapping("customers/search")
    public ResponseEntity<List<Customer>> getAllCustomer(@RequestParam("searchtext") String searchtext) {
        return ResponseEntity.ok().body(customerService.searchEmployees(searchtext));
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable("id") Integer customerId) {
        try {
            return ResponseEntity.ok(customerService.getCustomer(customerId));
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body("Không tìm thấy khách hàng");
        }
    }
}
