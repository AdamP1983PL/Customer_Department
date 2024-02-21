package com.customer_department.service.customer;

import com.customer_department.exceptions.ResourceNotFoundException;
import com.customer_department.exceptions.TaxNumberAlreadyExistsException;
import com.customer_department.model.customer.domain.Customer;
import com.customer_department.model.customer.repository.CustomerRepository;
import com.customer_department.service.customer.dto.CustomerDto;
import com.customer_department.service.customer.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> findAllCustomers() {
        log.info("====>>>> findAllCustomers() execution.");
        return customerRepository.findAll().stream()
                .map(customerMapper::mapToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        log.info("====>>>> findCustomerById(" + id + ") execution.");
        return customerMapper.mapToCustomerDto(customer);
    }

    @Override
    public CustomerDto findCustomerByName(String name) {
        Customer customer = customerRepository.findCustomerByCustomerName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Customer name", name));

        log.info("====>>>> findCustomerByName(" + name + ") execution.");
        return customerMapper.mapToCustomerDto(customer);
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Optional<Customer> customer = customerRepository
                .findCustomerByTaxNumber(customerDto.getTaxNumber());

        if (customer.isPresent()) {
            throw new TaxNumberAlreadyExistsException("Tax number", customerDto.getTaxNumber());
        }

        Customer savedCustomer = customerRepository.save(customerMapper.mapToCustomer(customerDto));
        log.info("====>>>> createCustomer(" + customerDto.getCustomerName() + ") execution.");
        return customerMapper.mapToCustomerDto(savedCustomer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Long id) {
        Customer customer = customerRepository.findById(id)
                .map(cust -> {
                    cust.setCustomerName(customerDto.getCustomerName());
                    cust.setTaxNumber(customerDto.getTaxNumber());
                    cust.setCountry(customerDto.getCountry());
                    cust.setCity(customerDto.getCity());
                    cust.setPostalCode(customerDto.getPostalCode());
                    cust.setStreet(customerDto.getStreet());
                    cust.setCustomerEmail(customerDto.getCustomerEmail());
                    cust.setCustomerPhoneNumber(customerDto.getCustomerPhoneNumber());
                    cust.setCustomerWebsite(customerDto.getCustomerWebsite());
                    cust.setActive(customerDto.isActive());
                    cust.setPaymentIsBlocked(customerDto.isPaymentIsBlocked());
                    cust.setTaxValue(customerDto.getTaxValue());
                    return customerRepository.save(cust);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        log.info("====>>>> updateCustomer() execution.");
        return customerMapper.mapToCustomerDto(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        customerRepository.deleteById(id);
        log.info("====>>>> Customer with id " + id + " deleted successfully.");
    }

}
