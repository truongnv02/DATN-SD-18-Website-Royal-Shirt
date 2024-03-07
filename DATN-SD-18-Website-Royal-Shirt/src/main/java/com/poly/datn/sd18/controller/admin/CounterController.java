package com.poly.datn.sd18.controller.admin;

import com.poly.datn.sd18.dto.response.ProductDetailCounterResponse;
import com.poly.datn.sd18.entity.ProductDetail;
import com.poly.datn.sd18.service.CounterService;
import com.poly.datn.sd18.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/counter")
public class CounterController {
    @Autowired
    CounterService counterService;

    @GetMapping()
    public String getAll(Model model){
        List<ProductDetailCounterResponse> lists = counterService.getListProductDetailCounter();
        model.addAttribute("lists",lists);
        return "/admin/counter/index";
    }
}