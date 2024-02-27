package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Category;
import com.poly.datn.sd18.repository.CategoryRepository;
import com.poly.datn.sd18.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getAllActive() {
        return categoryRepository.getAllActive();
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category, int id) {
        Category searchCategory = categoryRepository.findById(id).get();
        if (searchCategory != null) {
            searchCategory.setName(category.getName());
            return categoryRepository.save(searchCategory);
        }
        return null;
    }

    @Override
    public Category setStatus(int id) {
        Category searchCategory = categoryRepository.findById(id).get();
        if (searchCategory != null) {
            if (searchCategory.getStatus() == 1) {
                searchCategory.setStatus(0);
            } else {
                searchCategory.setStatus(1);
            }
            return categoryRepository.save(searchCategory);
        }
        return null;
    }
}
