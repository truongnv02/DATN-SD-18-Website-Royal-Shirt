package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.entity.Customer;
import com.poly.datn.sd18.exceptions.DataNotFoundException;
import com.poly.datn.sd18.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/counter")
@AllArgsConstructor
public class CounterRestController {


    CustomerService customerService;

    @PostMapping("/add-bill")
    public ResponseEntity<?> addBill() {
        return null;
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        return null;
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkOut() {
        return null;
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
