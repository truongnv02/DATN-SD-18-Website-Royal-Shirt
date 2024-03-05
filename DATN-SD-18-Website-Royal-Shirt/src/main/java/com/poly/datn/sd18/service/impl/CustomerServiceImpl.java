package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Customer;
import com.poly.datn.sd18.exceptions.DataNotFoundException;
import com.poly.datn.sd18.repository.CustomerRepository;
import com.poly.datn.sd18.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> searchEmployees(String searchText) {
        searchText = searchText.trim().toLowerCase();
        return customerRepository.searchByText(searchText);
    }

    @Override
    public Customer getCustomer(Integer customerId) throws DataNotFoundException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy khách hàng"));
    }
//    public Customer getCustomerByAccount(Integer accountId) {
//        return customerRepository.findByAccountId(accountId)
//                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khách hàng với tài khoản ID: " + accountId));
//    }
}
