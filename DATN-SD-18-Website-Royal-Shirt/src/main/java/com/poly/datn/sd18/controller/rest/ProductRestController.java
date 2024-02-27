package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.dto.ProductRequest;
import com.poly.datn.sd18.entity.Product;
import com.poly.datn.sd18.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/rest/product")
public class ProductRestController {
    @Autowired
    ProductService productService;

    @PostMapping("/checkDuplicateName")
    public ResponseEntity<?> checkDuplicateName(@RequestBody ProductRequest productRequest){
        List<Product> lists = productService.findByName(productRequest.getName());
        boolean isDuplicateName = false;
        if(lists.isEmpty()){
            isDuplicateName = true;
        }
        return ResponseEntity.ok(Map.of("isDuplicateName",isDuplicateName));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.add(productRequest));
    }

    @GetMapping("/formUpdate/{id}")
    public ResponseEntity<?> formUpdate(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable("id") int id) {
        return ResponseEntity.ok(productService.update(product, id));
    }

    @PostMapping("/setStatus/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id) {
        return ResponseEntity.ok(productService.setStatus(id));
    }
}
