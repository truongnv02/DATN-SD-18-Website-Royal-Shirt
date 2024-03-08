package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Size;

import java.util.List;

public interface SizeService {
    List<Size> getAllSizes();
    List<Size> findDistinctByIdAndName(Integer productId);
    Size findSizeById(Integer id);
}
