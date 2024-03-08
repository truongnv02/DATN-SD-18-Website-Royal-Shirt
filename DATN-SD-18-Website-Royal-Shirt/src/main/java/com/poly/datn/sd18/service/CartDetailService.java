package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Cart;
import com.poly.datn.sd18.entity.CartDetail;
import com.poly.datn.sd18.entity.Customer;
import com.poly.datn.sd18.entity.ProductDetail;
import com.poly.datn.sd18.model.dto.CartDetailDTO;
import com.poly.datn.sd18.model.response.CartDetailResponse;

import java.util.List;

public interface CartDetailService {
    CartDetail findByCartAndProductDetail(Cart cart, ProductDetail productDetail);
    List<CartDetail> findCartDetailByCustomer(Integer customerId);
}
