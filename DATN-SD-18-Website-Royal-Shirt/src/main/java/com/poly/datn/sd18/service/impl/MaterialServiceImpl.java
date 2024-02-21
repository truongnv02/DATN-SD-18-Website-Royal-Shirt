package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.repository.MaterialRepository;
import com.poly.datn.sd18.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
}
