package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Cart;
import com.poly.datn.sd18.entity.CartDetail;
import com.poly.datn.sd18.entity.Customer;
import com.poly.datn.sd18.entity.ProductDetail;
import com.poly.datn.sd18.model.dto.CartDetailDTO;
import com.poly.datn.sd18.model.response.CartDetailResponse;
import com.poly.datn.sd18.repository.CartDetailRepository;
import com.poly.datn.sd18.service.CartDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartDetailServiceImpl implements CartDetailService {
    private final CartDetailRepository cartDetailRepository;

    @Override
    public CartDetail findByCartAndProductDetail(Cart cart, ProductDetail productDetail) {
        return cartDetailRepository.findByCartAndProductDetail(cart, productDetail);
    }

    @Override
    public List<CartDetail> findCartDetailByCustomer(Integer customerId) {
        return cartDetailRepository.findCartDetailByCustomer(customerId);
    }
}
