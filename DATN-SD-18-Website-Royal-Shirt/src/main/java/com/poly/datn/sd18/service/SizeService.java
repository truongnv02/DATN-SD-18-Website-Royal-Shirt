package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Size;

import java.util.List;

public interface SizeService {
    List<Size> getAll();

    List<Size> getAllActive();

    Size findById(int id);

    List<Size> findByName(String name);

    Size add(Size size);

    Size update(Size size, int id);

    Size setStatus(int id);
}
