package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.dto.ImageRequest;
import com.poly.datn.sd18.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/rest/image")
public class ImageRestController {
    @Autowired
    ImageService imageService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ImageRequest imageRequest) {
        return ResponseEntity.ok(imageService.add(imageRequest));
    }

}
