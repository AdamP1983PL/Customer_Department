package com.customer_department.service.customer;

import com.customer_department.service.customer.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> findAllCustomers();

    CustomerDto findCustomerById(Long id);

    CustomerDto findCustomerByName(String name);

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(CustomerDto customerDto, Long id);

    void deleteCustomerById(Long id);

}
