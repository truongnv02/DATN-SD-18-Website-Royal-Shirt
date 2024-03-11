package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Size;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SizeService {
    List<Size> getAllSizes();
    List<Size> findDistinctByIdAndName(Integer productId);
    Size findSizeById(Integer id);
    List<Size> findSizeByProductIdAndColorId(Integer productId, Integer colorId);

}
