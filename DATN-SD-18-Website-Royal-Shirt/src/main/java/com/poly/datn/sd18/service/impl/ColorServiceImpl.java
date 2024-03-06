package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Color;
import com.poly.datn.sd18.repository.ColorRepository;
import com.poly.datn.sd18.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    @Override
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    @Override
    public Color findByIdColor(Integer id) {
        return colorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Color> getColorForProduct(Integer productId) {
        return colorRepository.findDistinctByProductDetails_ProductId(productId);
    }
}
