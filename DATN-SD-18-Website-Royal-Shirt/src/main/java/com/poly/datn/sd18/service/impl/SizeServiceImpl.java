package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Size;
import com.poly.datn.sd18.repository.SizeRepository;
import com.poly.datn.sd18.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;

    @Override
    public List<Size> getAllSizes() {
        return sizeRepository.findAll();
    }

    @Override
    public List<Size> findDistinctByIdAndName(Integer productId) {
        return sizeRepository.findDistinctByIdAndName(productId);
    }
}
