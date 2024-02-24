package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.dto.ImageRequest;
import com.poly.datn.sd18.entity.Image;
import com.poly.datn.sd18.repository.ImageRepository;
import com.poly.datn.sd18.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Override
    public Image add(ImageRequest imageRequest) {
        Image image = imageRequest.map(new Image());
        return imageRepository.save(image);
    }
}
