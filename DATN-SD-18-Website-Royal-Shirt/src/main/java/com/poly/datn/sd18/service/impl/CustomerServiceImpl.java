package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.repository.CustomerRepository;
import com.poly.datn.sd18.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
}
