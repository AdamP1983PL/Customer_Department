package com.customer_department.service;

import com.customer_department.dto.ContactPersonDto;
import com.customer_department.entity.ContactPerson;
import com.customer_department.exceptions.ResourceNotFoundException;
import com.customer_department.mappers.ContactPersonMapper;
import com.customer_department.repository.ContactPersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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



}
