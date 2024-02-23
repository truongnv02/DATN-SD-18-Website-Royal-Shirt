package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.dto.rest.ProductRestRequest;
import com.poly.datn.sd18.entity.Color;
import com.poly.datn.sd18.entity.Size;
import com.poly.datn.sd18.repository.ColorRepository;
import com.poly.datn.sd18.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/rest/add-product")
public class AddProductRestController {
    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ColorRepository colorRepository;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        List<Color> listColor = colorRepository.findAll();
        List<Size> listSize = sizeRepository.findAll();

        ProductRestRequest productRestRequest = new ProductRestRequest(listColor,listSize);

        return ResponseEntity.ok(productRestRequest);
    }
}
