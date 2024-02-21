package com.poly.datn.sd18.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private String image;
    private String description;
    private Integer quantity;
    private Integer status;
}
