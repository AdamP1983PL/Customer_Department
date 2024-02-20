package com.customer_department.controller;

import com.customer_department.dto.ContactPersonDto;
import com.customer_department.service.ContactPersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/contact-person")
public class ContactPersonController {

    private ContactPersonService contactPersonService;

    @GetMapping("/")
    public ResponseEntity<List<ContactPersonDto>> findAllContactPersons() {
        List<ContactPersonDto> contactPersonDtoList = contactPersonService.findAllContactPerson();
        log.info("====>>>> ContactPersonController -> findAllContactPersons() execution.");
        return new ResponseEntity<>(contactPersonDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactPersonDto> findContactPersonById(@PathVariable("id") Long id) {
        ContactPersonDto contactPersonDto = contactPersonService.findContactPersonById(id);
        log.info("====>>>> ContactPersonController -> findContactPersonById() execution.");
        return new ResponseEntity<>(contactPersonDto, HttpStatus.FOUND);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ContactPersonDto> findContactPersonByEmail(@PathVariable("email") String email) {
        ContactPersonDto contactPersonDto = contactPersonService.findContactPersonByEmail(email);
        log.info("====>>>> ContactPersonController -> findContactPersonByEmail() execution.");
        return new ResponseEntity<>(contactPersonDto, HttpStatus.FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<ContactPersonDto> createContactPerson(@RequestBody ContactPersonDto contactPersonDto) {
        ContactPersonDto createdContactPerson = contactPersonService.createContactPerson(contactPersonDto);
        log.info("====>>>> ContactPersonController -> createContactPerson() execution.");
        return new ResponseEntity<>(createdContactPerson, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactPersonDto> updateContactPerson(@RequestBody ContactPersonDto contactPersonDto,
                                                                @PathVariable("id") Long id) {
        ContactPersonDto updatedContactPersonDto = contactPersonService.updateContactPerson(contactPersonDto, id);
        log.info("====>>>> ContactPersonController -> updateContactPerson() execution.");
        return new ResponseEntity<>(updatedContactPersonDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactPerson(@PathVariable("id") Long id) {
        contactPersonService.deleteContactPersonById(id);
        log.info("====>>>> ContactPersonController -> deleteContactPerson() execution.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
