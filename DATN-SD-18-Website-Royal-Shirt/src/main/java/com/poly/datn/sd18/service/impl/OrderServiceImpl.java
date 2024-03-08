package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Customer;
import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.entity.OrderDetail;
import com.poly.datn.sd18.exceptions.DataNotFoundException;
import com.poly.datn.sd18.repository.*;
import com.poly.datn.sd18.requests.OrderCounterRequest;
import com.poly.datn.sd18.responses.OrderResponse;
import com.poly.datn.sd18.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final StaffRepository staffRepository;
    private final ProductDetailRepository productDetailRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderResponse createOrder(OrderCounterRequest orderCounterRequest) throws DataNotFoundException {
        Order newOrder = Order.builder()
                .phone(orderCounterRequest.getPhoneNumber())
                .address(orderCounterRequest.getFullAddress())
                .note(orderCounterRequest.getNote())
                .confirmDate(null)
                .staff(staffRepository.findById(orderCounterRequest.getEmployeeID())
                        .orElseThrow(() -> new DataNotFoundException("Staff not found")))
                .status(1)
                .totalPrice(orderCounterRequest.getTotalMoney())
                .username(orderCounterRequest.getCustomerName())
                .confirmDate(Date.valueOf(LocalDate.now()))
                .successDate(Date.valueOf(LocalDate.now()))
                .build();
        if (customerRepository.findById(orderCounterRequest.getCustomerID()).isPresent()) {
            newOrder.setCustomer(customerRepository.findById(orderCounterRequest.getCustomerID())
                    .orElseThrow(() -> new DataNotFoundException("Customer not found!")));
        } else {
            newOrder.setCustomer(this.getCustomerIfEmpty());
        }
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderRepository.save(newOrder);
        for (var product : orderCounterRequest.getProducts()) {
            orderDetails.add(orderDetailRepository.save(OrderDetail.builder()
                    .order(newOrder)
                    .status(1)
                    .quantity(product.getQuantity())
                    .productDetail(product)
                    .price(product.getPrice())
                    .build()));
        }
        return OrderResponse.builder()
                .order(newOrder)
                .orderDetails(orderDetails)
                .build();
    }

    @Override
    public OrderResponse getBill(Integer orderId) throws DataNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("Order not found"));
        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrder(order);
        return OrderResponse.builder()
                .order(order)
                .orderDetails(orderDetails)
                .build();
    }

    // if the customer id is null, generate or using default customer
    private Customer getCustomerIfEmpty() {
        if (customerRepository.findCustomerByName("COUNTER").isPresent()) {
            return customerRepository.findCustomerByName("COUNTER").get();
        } else {
            return customerRepository.save(Customer.builder()
                            .name("COUNTER")
                            .email("")
                            .avatar("")
                            .status(1)
                            .phone("123456789")
                    .build());
        }
    }
}
