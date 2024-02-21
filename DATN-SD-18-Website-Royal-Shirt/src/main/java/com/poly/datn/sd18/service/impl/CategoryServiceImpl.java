package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.repository.CategoryRepository;
import com.poly.datn.sd18.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
}
