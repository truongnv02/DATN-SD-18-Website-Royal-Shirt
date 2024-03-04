package com.poly.datn.sd18.controller.client;

import com.poly.datn.sd18.entity.*;
import com.poly.datn.sd18.model.response.ProductResponse;
import com.poly.datn.sd18.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductClientController {
    private final BrandService brandService;
    private final SizeService sizeService;
    private final CategoryService categoryService;
    private final ColorService colorService;
    private final ProductService productService;
    private final ProductDetailService productDetailService;

    @GetMapping("/products")
    public String getAllProducts(Model model,
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "keyword", required = false) String keyword) {
        List<Brand> listBrands = brandService.getAllBrands();
        List<Category> listCategories = categoryService.getAllCategories();
        Page<Product> listsProducts;

        if (keyword != null && !keyword.isEmpty()) {
            listsProducts = productService.searchProducts(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        } else {
            listsProducts = productService.pageProducts(pageNo);
        }
        for (Product p : listsProducts.getContent()) {
            // Thực hiện các thao tác mong muốn với mỗi sản phẩm (product) ở đây
            // Ví dụ:
            System.out.println("Product ID: " + p.getId());
            System.out.println("Product Name: " + p.getName());
        }

        model.addAttribute("listBrand", listBrands);
        model.addAttribute("listCategory", listCategories);

        model.addAttribute("totalPage", listsProducts.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listProducts", listsProducts);
        return "client/product/product";
    }

    @GetMapping("/single-product/{id}")
    public String singleProduct(Model model, @PathVariable("id") Integer id) {
        Product product = productService.findProductById(id);
        List<Size> listsSize = sizeService.getAllSizes();
        List<Color> listsColor = colorService.getAllColors();
        model.addAttribute("listSize", listsSize);
        model.addAttribute("listColor", listsColor);
        model.addAttribute("product", product);
        return "client/product/single-product";
    }

    @GetMapping("/filter")
    public String filter() {
        return "client/product/product";
    }

}
