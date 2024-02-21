package com.customer_department.mappers;

import com.customer_department.dto.CustomerDto;
import com.customer_department.entity.ContactPerson;
import com.customer_department.entity.Customer;
import com.customer_department.service.ContactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    @Autowired
    ContactPersonService contactPersonService;

    @Autowired
    ContactPersonMapper contactPersonMapper;


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
                .contactPersons(customerDto.getContactPersonsId().stream()
                        .map(this::mapContactPersonIdToContactPersonObject)
                        .collect(Collectors.toList()))
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
                .contactPersonsId(customer.getContactPersons().stream()
                        .map(ContactPerson::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public ContactPerson mapContactPersonIdToContactPersonObject(Long id) {
        return contactPersonMapper.mapToContactPerson(contactPersonService.findContactPersonById(id));
    }

}
