package com.customer_department.controller;

import com.customer_department.service.customer.CustomerServiceImpl;
import com.customer_department.service.customer.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {
        List<CustomerDto> customerDtoList = customerServiceImpl.findAllCustomers();
        log.info("====>>>> findAllCustomers() execution");
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable("id") Long id) {
        CustomerDto customerDto = customerServiceImpl.findCustomerById(id);
        log.info("====>>>> findAllCustomerById(\"" + id + "\") execution");
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CustomerDto>> findCustomersByName(@PathVariable("name") String name) {
        List<CustomerDto> customerDtoList = customerServiceImpl.findCustomersByName(name);
        log.info("====>>>> findCustomersByName(\"" + name + "\") execution");
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomerDto = customerServiceImpl.createCustomer(customerDto);
        log.info("====>>>> createCustomer() execution");
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto,
                                                      @PathVariable Long id) {
        CustomerDto updatedCustomerDto = customerServiceImpl.updateCustomer(customerDto, id);
        log.info("====>>>> updateCustomer() execution");
        return new ResponseEntity<>(updatedCustomerDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        customerServiceImpl.deleteCustomerById(id);
        log.info("====>>>> deleteCustomer(\"id: " + id + "\") execution");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
