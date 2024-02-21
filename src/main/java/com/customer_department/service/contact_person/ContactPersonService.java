package com.customer_department.service.contact_person;

import com.customer_department.service.contact_person.dto.ContactPersonDto;

import java.util.List;

public interface ContactPersonService {

    List<ContactPersonDto> findAllContactPerson();

    ContactPersonDto findContactPersonById(Long id);

    ContactPersonDto findContactPersonByEmail(String email);

    ContactPersonDto createContactPerson(ContactPersonDto contactPersonDto);

    ContactPersonDto updateContactPerson(ContactPersonDto contactPersonDto, Long id);

    void deleteContactPersonById(Long id);
}
