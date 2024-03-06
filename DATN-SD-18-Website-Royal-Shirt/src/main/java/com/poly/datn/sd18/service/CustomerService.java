package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Customer;
import com.poly.datn.sd18.model.dto.CustomerDTO;
import com.poly.datn.sd18.model.request.CustomerRequest;

public interface CustomerService {
    Customer createCustomer(CustomerDTO customerDTO);
    Customer loginCustomer(CustomerRequest customerRequest);
}
