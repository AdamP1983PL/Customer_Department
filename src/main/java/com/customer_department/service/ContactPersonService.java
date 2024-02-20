package com.customer_department.service;

import com.customer_department.dto.ContactPersonDto;
import com.customer_department.entity.ContactPerson;
import com.customer_department.exceptions.EmailAlreadyExistsException;
import com.customer_department.exceptions.ResourceNotFoundException;
import com.customer_department.mappers.ContactPersonMapper;
import com.customer_department.repository.ContactPersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ContactPersonService {

    private ContactPersonRepository contactPersonRepository;
    private ContactPersonMapper contactPersonMapper;

    public List<ContactPersonDto> findAllContactPerson() {
        log.info("====>>>> findAllContactPerson() execution.");
        return contactPersonRepository.findAll().stream()
                .map(contactPersonMapper::mapToContactPersonDto)
                .collect(Collectors.toList());
    }

    public ContactPersonDto findContactPersonById(Long id) {
        ContactPerson contactPerson = contactPersonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact person", "id", id));

        log.info("====>>>> findContactPersonById(" + id + ") execution.");
        return contactPersonMapper.mapToContactPersonDto(contactPerson);
    }

    public ContactPersonDto findContactPersonByEmail(String email) {
        ContactPerson contactPerson = contactPersonRepository.findContactPersonByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Person Email: ", email));

        log.info("====>>>> findContactPersonByEmail(" + email + ") execution");
        return contactPersonMapper.mapToContactPersonDto(contactPerson);
    }

    public ContactPersonDto createContactPerson(ContactPersonDto contactPersonDto) {
        Optional<ContactPerson> contactPerson = contactPersonRepository
                .findContactPersonByEmail(contactPersonDto.getEmail());

        if (contactPerson.isPresent()) {
            throw new EmailAlreadyExistsException("Email", contactPersonDto.getEmail());
        }

        ContactPerson savedContactPerson = contactPersonRepository
                .save(contactPersonMapper.mapToContactPerson(contactPersonDto));
        log.info("====>>>> createContactPerson() execution.");
        return contactPersonMapper.mapToContactPersonDto(savedContactPerson);
    }

    public ContactPersonDto updateContactPerson(ContactPersonDto contactPersonDto, Long id) {
        ContactPerson contactPerson = contactPersonRepository.findById(id)
                .map(person -> {
                    person.setFirstName(contactPersonDto.getFirstName());
                    person.setLastName(contactPersonDto.getLastName());
                    person.setEmail(contactPersonDto.getEmail());
                    person.setPhoneNumber(contactPersonDto.getPhoneNumber());
                    return contactPersonRepository.save(person);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Contact Person", "id", id));

        log.info("====>>>> updateContactPerson() execution.");
        return contactPersonMapper.mapToContactPersonDto(contactPerson);
    }

    public void deleteContactPersonById(Long id) {
        contactPersonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Person", "id", id));

        contactPersonRepository.deleteById(id);
        log.info("====>>>> Contact Person with id: " + id + " deleted successfully.");
    }

}
