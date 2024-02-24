package com.poly.datn.sd18.service;

import com.poly.datn.sd18.dto.ImageRequest;
import com.poly.datn.sd18.entity.Image;

public interface ImageService {
    Image add(ImageRequest imageRequest);
}
