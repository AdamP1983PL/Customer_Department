package com.customer_department.mappers;

import com.customer_department.dto.ContactPersonDto;
import com.customer_department.entity.ContactPerson;
import org.springframework.stereotype.Component;

@Component
public class ContactPersonMapper {

    public ContactPerson mapToContactPerson(ContactPersonDto contactPersonDto) {
        return ContactPerson.builder()
                .id(contactPersonDto.getId())
                .firstName(contactPersonDto.getFirstName())
                .lastName(contactPersonDto.getLastName())
                .email(contactPersonDto.getEmail())
                .phoneNumber(contactPersonDto.getPhoneNumber())
                .customer(contactPersonDto.getCustomer())
                .build();
    }

    public ContactPersonDto mapToContactPersonDto(ContactPerson contactPerson) {
        return ContactPersonDto.builder()
                .id(contactPerson.getId())
                .firstName(contactPerson.getFirstName())
                .lastName(contactPerson.getLastName())
                .email(contactPerson.getEmail())
                .phoneNumber(contactPerson.getEmail())
                .customer(contactPerson.getCustomer())
                .build();
    }


}
