package com.poly.datn.sd18.controller.client;

import com.poly.datn.sd18.entity.CartDetail;
import com.poly.datn.sd18.entity.Customer;
import com.poly.datn.sd18.model.dto.CartDetailDTO;
import com.poly.datn.sd18.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;
    private final SizeService sizeService;
    private final ColorService colorService;
    private final CustomerService customerService;
    private final CartService cartService;
    private final CartDetailService cartDetailService;
    private final ProductDetailService productDetailService;
    private final HttpSession session;

    @GetMapping("/cart")
    public String cart(Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/loginPage";
        }
        List<CartDetail> cartDetails = cartDetailService.findCartDetailByCustomer(customer.getId());
        model.addAttribute("cartDetails", cartDetails);
        return "client/cart/cart";
    }

    @PostMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable("id") Integer productDetailId,
                            @PathVariable("colorId") Integer colorId,
                            @PathVariable("sizeId") Integer sizeId,
                            @RequestParam("quantity") Integer quantity,
                            Model model,
                            CartDetailDTO cartDetailDTO) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/loginPage";
        }

        return "redirect:/single-product";
    }
}
