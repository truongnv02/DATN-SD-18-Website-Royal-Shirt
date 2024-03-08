package com.poly.datn.sd18.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CartDetailResponse {
    private Integer id;
    private String image;
    private String nameProduct;
    private String nameColor;
    private String nameSize;
    private Integer quantity;
    private Float price;
    private Integer status;
}
