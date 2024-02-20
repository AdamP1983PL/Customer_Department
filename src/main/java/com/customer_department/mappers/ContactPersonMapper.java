package com.customer_department.mappers;

import com.customer_department.dto.ContactPersonDto;
import com.customer_department.entity.ContactPerson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContactPersonMapper {

    public ContactPerson mapToContactPerson(ContactPersonDto contactPersonDto) {
        return ContactPerson.builder()
                .id(contactPersonDto.getId())
                .firstName(contactPersonDto.getFirstName())
                .lastName(contactPersonDto.getLastName())
                .email(contactPersonDto.getEmail())
                .phoneNumber(contactPersonDto.getPhoneNumber())
                .build();
    }

    public ContactPersonDto mapToContactPersonDto(ContactPerson contactPerson) {
        return ContactPersonDto.builder()
                .id(contactPerson.getId())
                .firstName(contactPerson.getFirstName())
                .lastName(contactPerson.getLastName())
                .email(contactPerson.getEmail())
                .phoneNumber(contactPerson.getPhoneNumber())
                .build();
    }

}
