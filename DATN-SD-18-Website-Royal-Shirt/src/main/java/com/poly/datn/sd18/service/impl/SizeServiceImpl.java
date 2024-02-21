package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.repository.SizeRepository;
import com.poly.datn.sd18.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;
}
