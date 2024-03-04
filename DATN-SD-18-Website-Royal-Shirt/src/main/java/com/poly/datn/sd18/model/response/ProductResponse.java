package com.poly.datn.sd18.model.response;

import com.poly.datn.sd18.entity.Color;
import com.poly.datn.sd18.entity.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String name;
    private String image;
    private Float price;
}
