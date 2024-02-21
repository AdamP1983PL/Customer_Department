package com.customer_department.model.contact_person.repository;

import com.customer_department.model.contact_person.domain.ContactPerson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactPersonRepositoryTest {
    private ContactPerson contactPerson;

    @Autowired
    ContactPersonRepository contactPersonRepository;

    @BeforeEach()
    void initialise() {
        contactPerson = ContactPerson.builder()
                .id(1L)
                .firstName("test first name")
                .lastName("test last name")
                .email("test@test.com")
                .phoneNumber("111-111-111")
                .build();
    }

    @AfterEach()
    void cleanUp() {
        contactPersonRepository.deleteAll();
    }

    @Test
    @DisplayName("Testing findContactPersonByEmail() method")
    public void givenContactPersonId_whenFindContactPersonById_thenReturnContactPersonObject() {
        // given
        ContactPerson savedContactPerson = contactPersonRepository.save(contactPerson);

        // when
        Optional<ContactPerson> testedContactPerson = contactPersonRepository
                .findContactPersonByEmail(contactPerson.getEmail());

        // then
        assertNotNull(testedContactPerson);
        assertAll(
                () -> assertEquals("test first name", testedContactPerson.get().getFirstName()),
                () -> assertEquals("test last name", testedContactPerson.get().getLastName())
        );
    }
}
