package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Color;

import java.util.List;

public interface ColorService {
    List<Color> getAllColors();
    Color findByIdColor(Integer id);
    List<Color> getColorForProduct(Integer productId);
}
