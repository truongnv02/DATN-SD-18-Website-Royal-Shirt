package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Brand;
import com.poly.datn.sd18.repository.BrandRepository;
import com.poly.datn.sd18.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}
