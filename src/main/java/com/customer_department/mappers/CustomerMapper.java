package com.customer_department.mappers;

import com.customer_department.dto.CustomerDto;
import com.customer_department.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer mapToCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .customerName(customerDto.getCustomerName())
                .taxNumber(customerDto.getTaxNumber())
                .country(customerDto.getCountry())
                .city(customerDto.getCity())
                .postalCode(customerDto.getPostalCode())
                .street(customerDto.getStreet())
                .customerEmail(customerDto.getCustomerEmail())
                .customerPhoneNumber(customerDto.getCustomerPhoneNumber())
                .customerWebsite(customerDto.getCustomerWebsite())
                .isActive(customerDto.isActive())
                .paymentIsBlocked(customerDto.isPaymentIsBlocked())
                .paymentMethod(customerDto.getPaymentMethod())
                .taxValue(customerDto.getTaxValue())
                .contactPersons(customerDto.getContactPersons())
                .build();
    }

    public CustomerDto mapToCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .taxNumber(customer.getTaxNumber())
                .country(customer.getCountry())
                .city(customer.getCity())
                .postalCode(customer.getPostalCode())
                .street(customer.getStreet())
                .customerEmail(customer.getCustomerEmail())
                .customerPhoneNumber(customer.getCustomerPhoneNumber())
                .customerWebsite(customer.getCustomerWebsite())
                .isActive(customer.isActive())
                .paymentIsBlocked(customer.isPaymentIsBlocked())
                .paymentMethod(customer.getPaymentMethod())
                .taxValue(customer.getTaxValue())
                .contactPersons(customer.getContactPersons())
                .build();
    }

}
