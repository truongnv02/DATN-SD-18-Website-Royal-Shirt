package com.poly.datn.sd18.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/revenue")
public class RevenueController {
    @GetMapping("")
    public String admin() {
        return "admin/revenue/index";
    }
}
