package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.*;
import com.poly.datn.sd18.model.dto.CartDetailDTO;
import com.poly.datn.sd18.repository.*;
import com.poly.datn.sd18.service.CartDetailService;
import com.poly.datn.sd18.service.ColorService;
import com.poly.datn.sd18.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartDetailServiceImpl implements CartDetailService {
    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;
    private final ProductDetailRepository productDetailRepository;
    private final SizeService sizeService;
    private final ColorService colorService;

    @Override
    public CartDetail findByCartAndProductDetail(Cart cart, ProductDetail productDetail) {
        return cartDetailRepository.findByCartAndProductDetail(cart, productDetail);
    }

    @Override
    public List<CartDetail> findCartDetailByCustomer(Integer customerId) {
        return cartDetailRepository.findCartDetailByCustomer(customerId);
    }

    @Override
    public void addToCart(Integer productId, Integer customerId, CartDetailDTO cartDetailDTO) {
        ProductDetail productDetail = productDetailRepository
                .findByProductIdAndColorIdAndSizeId(
                        productId,
                        cartDetailDTO.getProductDetail().getSize().getId(),
                        cartDetailDTO.getProductDetail().getColor().getId());
        if (productDetail == null) {
            return;
        }
        Cart cart = cartRepository.findByCustomerId(customerId);
        if (cart == null) {
            return;
        }
        CartDetail cartDetail = cartDetailRepository.findByCartAndProductDetail(cart, productDetail);

        if (cartDetail == null) {
            cartDetail = new CartDetail();
            cartDetail.setProductDetail(productDetail);
            cartDetail.setCart(cart);
            cartDetail.setQuantity(cartDetailDTO.getQuantity());
            if (productDetail != null) {
                cartDetail.setPrice(productDetail.getPrice() * cartDetailDTO.getQuantity());
            }
            cartDetail.setStatus(0);
            cartDetailRepository.save(cartDetail);
        } else {
            cartDetail.setQuantity(cartDetailDTO.getQuantity() + 1);
            if (productDetail != null) {
                cartDetail.setPrice(productDetail.getPrice() * cartDetailDTO.getQuantity());
            }
            cartDetailRepository.save(cartDetail);
        }
    }

    @Transactional
    @Override
    public void deleteIdProductDetailAndIdCustomer(Integer productDetailId, Integer customerId) {
        cartDetailRepository.deleteIdProductDetailAndIdCustomer(productDetailId, customerId);
    }

    @Transactional
    @Override
    public void updateByProductDetailIdAndCustomerId(Integer productDetailId, Integer customerId, Integer quantity) {
        Cart cart = cartRepository.findByCustomerId(customerId);
        ProductDetail productDetail = productDetailRepository.findByProductDetailId(productDetailId);
        CartDetail cartDetail = cartDetailRepository.findByCartAndProductDetail(cart, productDetail);

        cartDetail.setQuantity(quantity);
        cartDetail.setPrice(quantity * productDetail.getPrice());
        cartDetailRepository.save(cartDetail);
    }

    @Override
    public Float getSumPriceByCustomerId(Integer customerId) {
        return cartDetailRepository.getSumPriceByCustomerId(customerId);
    }
}
