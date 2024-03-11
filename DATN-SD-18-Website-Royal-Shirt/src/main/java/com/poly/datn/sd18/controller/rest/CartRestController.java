package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.entity.Customer;
import com.poly.datn.sd18.entity.Product;
import com.poly.datn.sd18.model.dto.CartDetailDTO;
import com.poly.datn.sd18.service.CartDetailService;
import com.poly.datn.sd18.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CartRestController {
    private final ProductService productService;
    private final CartDetailService cartDetailService;
    private final HttpSession session;

    @PostMapping("/add-to-cart/{id}")
    @ResponseBody
    public ResponseEntity<?> addToCart(@PathVariable("id") Integer productId,
                                       @RequestBody CartDetailDTO cartDetailDTO,
                                       BindingResult result) {
        Product product = productService.findProductById(productId);
        Customer customer = (Customer) session.getAttribute("customer");

        cartDetailService.addToCart(productId, customer.getId(), cartDetailDTO);
        return ResponseEntity.ok("Successfully");
    }
}
