package com.customer_department.model.customer.repository;

import com.customer_department.model.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByCustomerName(String customerName);

    Optional<Customer> findCustomerByTaxNumber(String taxNumber);

}
