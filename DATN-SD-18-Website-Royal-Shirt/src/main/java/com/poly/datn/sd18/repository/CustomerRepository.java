package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("Select Count(c.id) From Customer c")
    int countCustomer();
}
