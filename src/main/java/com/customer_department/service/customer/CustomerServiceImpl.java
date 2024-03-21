package com.customer_department.service.customer;

import com.customer_department.exceptions.ResourceNotFoundException;
import com.customer_department.exceptions.TaxNumberAlreadyExistsException;
import com.customer_department.model.customer.domain.Customer;
import com.customer_department.model.customer.repository.CustomerRepository;
import com.customer_department.service.customer.dto.CustomerDto;
import com.customer_department.service.customer.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

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

        log.info("====>>>> findCustomerById(\"" + id + "\") execution.");
        return customerMapper.mapToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> findCustomersByName(String name) {
        List<Customer> customers = customerRepository.findCustomerByCustomerNameContaining(name);

        log.info("====>>>> findCustomersByName(\"" + name + "\") execution.");
        return customers.stream()
                .map(customerMapper::mapToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Optional<Customer> customer = customerRepository.findCustomerByTaxNumber(customerDto.getTaxNumber());
        if (customer.isPresent()) {
            throw new TaxNumberAlreadyExistsException("Tax number", customerDto.getTaxNumber());
        }

        Customer savedCustomer = customerRepository.save(customerMapper.mapToCustomer(customerDto));
        log.info("====>>>> createCustomer(" + customerDto.getCustomerName() + ") execution.");
        return customerMapper.mapToCustomerDto(savedCustomer);
    }

// todo Postman tests: savedCustomer "isActive" is always false!!!

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
                    cust.setContactPersonName(customerDto.getContactPersonName());
                    cust.setContactPersonEmail(customerDto.getContactPersonEmail());
                    cust.setContactPersonPhone(customerDto.getContactPersonPhone());
                    return customerRepository.save(cust);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        log.info("====>>>> updateCustomer() execution.");
        return customerMapper.mapToCustomerDto(customer);
    }

    @Override
    public CustomerDto mvcUpdateCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerDto.getId()));
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setTaxNumber(customerDto.getTaxNumber());
        customer.setCountry(customerDto.getCountry());
        customer.setCity(customerDto.getCity());
        customer.setPostalCode(customerDto.getPostalCode());
        customer.setStreet(customerDto.getStreet());
        customer.setCustomerEmail(customerDto.getCustomerEmail());
        customer.setCustomerPhoneNumber(customerDto.getCustomerPhoneNumber());
        customer.setCustomerWebsite(customerDto.getCustomerWebsite());
        customer.setActive(customerDto.isActive());
        customer.setPaymentIsBlocked(customerDto.isPaymentIsBlocked());
        customer.setTaxValue(customerDto.getTaxValue());
        customer.setContactPersonName(customerDto.getContactPersonName());
        customer.setContactPersonEmail(customerDto.getContactPersonEmail());
        customer.setContactPersonPhone(customerDto.getContactPersonPhone());
        Customer savedCustomer = customerRepository.save(customer);
        log.info("====>>>> mvcUpdateCustomer(" + customerDto + ") execution.");
        return customerMapper.mapToCustomerDto(savedCustomer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        customerRepository.delete(customer);
        log.info("====>>>> Customer with id " + id + " deleted successfully.");
    }

}
