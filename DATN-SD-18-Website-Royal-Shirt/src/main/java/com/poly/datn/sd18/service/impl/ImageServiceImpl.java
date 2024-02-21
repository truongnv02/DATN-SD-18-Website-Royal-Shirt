package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.repository.ImageRepository;
import com.poly.datn.sd18.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
}
