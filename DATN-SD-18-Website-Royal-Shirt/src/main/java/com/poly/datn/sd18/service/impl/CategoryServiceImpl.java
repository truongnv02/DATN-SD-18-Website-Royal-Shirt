package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Category;
import com.poly.datn.sd18.repository.CategoryRepository;
import com.poly.datn.sd18.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
