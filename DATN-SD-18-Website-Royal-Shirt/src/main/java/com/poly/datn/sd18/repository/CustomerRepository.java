package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT cus FROM Customer cus WHERE LOWER(cus.name) LIKE CONCAT('%', :searchText, '%') OR LOWER(cus.phone) LIKE CONCAT('%', :searchText, '%')")
    public List<Customer> searchByText(@Param("searchText") String searchText);
//    Optional<Customer> findByAccountId(Integer accountId);
}
