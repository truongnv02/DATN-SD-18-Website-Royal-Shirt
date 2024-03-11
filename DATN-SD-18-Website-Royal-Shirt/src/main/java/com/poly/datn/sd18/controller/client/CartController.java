package com.poly.datn.sd18.controller.client;

import com.poly.datn.sd18.entity.*;
import com.poly.datn.sd18.model.dto.CartDetailDTO;
import com.poly.datn.sd18.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;
    private final CartDetailService cartDetailService;
    private final HttpSession session;

    @GetMapping("/cart")
    public String cart(Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/loginPage";
        }else {
            Float sumPrice = cartDetailService.getSumPriceByCustomerId(customer.getId());
            if (sumPrice == null) {
                sumPrice = 0f;
            }
            model.addAttribute("sumPrice", sumPrice.intValue());
            List<CartDetail> cartDetails = cartDetailService.findCartDetailByCustomer(customer.getId());
            model.addAttribute("cartDetails", cartDetails);
            return "client/cart/cart";
        }
    }

    @ModelAttribute("cartDetail")
    public CartDetailDTO cartDetailUser() {
        return new CartDetailDTO();
    }

    @GetMapping("/cart/{id}")
    public String deleteCart(@PathVariable("id") Integer idProductDetail) {
        Customer customer = (Customer) session.getAttribute("customer");
        cartDetailService.deleteIdProductDetailAndIdCustomer(idProductDetail, customer.getId());
        return "redirect:/cart";
    }

    @Transactional
    @PostMapping("/cart/update/{id}")
    public String updateCart(@PathVariable("id") Integer idProductDetail,
                             @RequestParam("quantity") Integer quantity) {
        Customer customer = (Customer) session.getAttribute("customer");
        cartDetailService.updateByProductDetailIdAndCustomerId(idProductDetail, customer.getId(), quantity);
        return "redirect:/cart";
    }
}
