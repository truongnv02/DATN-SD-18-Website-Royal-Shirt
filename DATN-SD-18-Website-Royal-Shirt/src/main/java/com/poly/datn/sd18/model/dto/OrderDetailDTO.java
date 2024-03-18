package com.poly.datn.sd18.model.dto;

import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.entity.OrderDetail;
import com.poly.datn.sd18.entity.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private Integer orderId;
    private Integer productDetailId;
    private Integer quantity;
    private Float price;
    private Integer status;

    public OrderDetail map (OrderDetail orderDetail){
        orderDetail.setOrder(Order.builder().id(this.orderId).build());
        orderDetail.setProductDetail(ProductDetail.builder().id(this.productDetailId).build());
        orderDetail.setQuantity(this.quantity);
        orderDetail.setPrice(this.price);
        orderDetail.setStatus(this.status);
        return orderDetail;
    }
}
