package com.poly.datn.sd18.controller.admin;

import com.poly.datn.sd18.dto.ProductRequest;
import com.poly.datn.sd18.dto.ProductResponse;
import com.poly.datn.sd18.entity.Brand;
import com.poly.datn.sd18.entity.Category;
import com.poly.datn.sd18.entity.Material;
import com.poly.datn.sd18.entity.Product;
import com.poly.datn.sd18.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ColorService colorService;

    @Autowired
    MaterialService materialService;

    @Autowired
    SizeService sizeService;

    @GetMapping()
    public String getAll(Model model) {
        List<Brand> listBrand = brandService.getAllActive();
        model.addAttribute("listBrand", listBrand);

        List<Category> listCategory = categoryService.getAllActive();
        model.addAttribute("listCategory", listCategory);

        List<Material> listMaterial = materialService.getAllActive();
        model.addAttribute("listMaterial", listMaterial);

        List<ProductResponse> lists = productService.getAll();
        model.addAttribute("lists", lists);
        return "/admin/product/index";
    }


}
