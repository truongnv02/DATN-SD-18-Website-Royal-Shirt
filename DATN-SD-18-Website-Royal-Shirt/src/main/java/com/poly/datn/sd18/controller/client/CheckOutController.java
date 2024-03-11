package com.poly.datn.sd18.controller.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CheckOutController {

    @GetMapping("/checkout")
    public String checkOut() {
        return "client/cart/checkout";
    }
}
