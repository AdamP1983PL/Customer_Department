package com.customer_department.service.contact_person;

import com.customer_department.service.contact_person.dto.ContactPersonDto;

import java.util.List;

public interface ContactPersonService {

    public List<ContactPersonDto> findAllContactPerson();

    public ContactPersonDto findContactPersonById(Long id);

    public ContactPersonDto findContactPersonByEmail(String email);

    public ContactPersonDto createContactPerson(ContactPersonDto contactPersonDto);

    public ContactPersonDto updateContactPerson(ContactPersonDto contactPersonDto, Long id);

    public void deleteContactPersonById(Long id);
}
