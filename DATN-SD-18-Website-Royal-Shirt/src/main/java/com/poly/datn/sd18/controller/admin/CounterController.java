package com.poly.datn.sd18.controller.admin;

import com.poly.datn.sd18.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CounterController {

    private final ProductDetailService productDetailService;

    @GetMapping("/counter")
    public String getCounterPage(Model model) {
        model.addAttribute("listProductDetails", productDetailService.getProductDetails());
        return "/admin/cart_counter/counter";
    }
    @GetMapping("/counter1")
    public String getCounterPage1(Model model) {
        model.addAttribute("listProductDetails", productDetailService.getProductDetails());
        return "/admin/ban_hang/counter";
    }

}
