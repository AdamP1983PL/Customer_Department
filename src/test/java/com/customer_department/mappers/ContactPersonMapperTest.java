package com.customer_department.mappers;

import com.customer_department.dto.ContactPersonDto;
import com.customer_department.entity.ContactPerson;
import com.customer_department.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactPersonMapperTest {

    private Customer customer;
    private ContactPerson contactPerson;
    private ContactPersonDto contactPersonDto;

    private ContactPersonMapper contactPersonMapper = new ContactPersonMapper();

    @BeforeEach()
    void initialize() {
        contactPerson = ContactPerson.builder()
                .id(1L)
                .firstName("test first name")
                .lastName("test last name")
                .email("test@test.com")
                .phoneNumber("111-111-111")
                .build();

        contactPersonDto = ContactPersonDto.builder()
                .id(1L)
                .firstName("test first name")
                .lastName("test last name")
                .email("test@test.com")
                .phoneNumber("111-111-111")
                .build();
    }

    @Test
    @DisplayName("Testing mapToContactPerson() method.")
    public void givenContactPersonDtoObject_whenMapToContactPerson_thenReturnMappedObject() {
        // given

        // when
        ContactPerson mappedContactPerson = contactPersonMapper.mapToContactPerson(contactPersonDto);

        // then
        assertAll(
                () -> assertNotNull(mappedContactPerson),
                () -> assertEquals(1L, mappedContactPerson.getId()),
                () -> assertEquals("test first name", mappedContactPerson.getFirstName()),
                () -> assertEquals("test last name", mappedContactPerson.getLastName()),
                () -> assertEquals("test@test.com", mappedContactPerson.getEmail()),
                () -> assertEquals("111-111-111", mappedContactPerson.getPhoneNumber())
        );
    }

    @Test
    @DisplayName("Testing mapToContactPersonDto() method.")
    public void givenContactPersonObject_whenMapToContactPersonDto_thenReturnMappedObject() {
        // given

        // when
        ContactPersonDto mappedContactPersonDto = contactPersonMapper.mapToContactPersonDto(contactPerson);

        // then
        assertAll(
                () -> assertNotNull(mappedContactPersonDto),
                () -> assertEquals(1L, mappedContactPersonDto.getId()),
                () -> assertEquals("test first name", mappedContactPersonDto.getFirstName()),
                () -> assertEquals("test last name", mappedContactPersonDto.getLastName()),
                () -> assertEquals("test@test.com", mappedContactPersonDto.getEmail()),
                () -> assertEquals("111-111-111", mappedContactPersonDto.getPhoneNumber())
                );
    }

}
