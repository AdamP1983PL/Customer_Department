//package com.customer_department.service.contact_person;
//
//import com.customer_department.service.contact_person.dto.ContactPersonDto;
//import com.customer_department.model.contact_person.domain.ContactPerson;
//import com.customer_department.exceptions.ResourceNotFoundException;
//import com.customer_department.service.contact_person.mapper.ContactPersonMapper;
//import com.customer_department.model.contact_person.repository.ContactPersonRepository;
//import com.customer_department.service.contact_person.ContactPersonServiceImpl;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ContactPersonServiceImplTest {
//
//    private ContactPerson contactPerson;
//    private ContactPersonDto contactPersonDto;
//
//    @BeforeEach()
//    void initialize() {
//        contactPerson = ContactPerson.builder()
//                .id(1L)
//                .firstName("test first name")
//                .lastName("test last name")
//                .email("test@test.com")
//                .phoneNumber("111-111-111")
//                .build();
//
//        contactPersonDto = ContactPersonDto.builder()
//                .id(1L)
//                .firstName("test first name")
//                .lastName("test last name")
//                .email("test@test.com")
//                .phoneNumber("111-111-111")
//                .build();
//    }
//
//    @AfterEach()
//    void cleanUp() {
//        contactPersonRepository.deleteAll();
//    }
//
//    @Mock
//    private ContactPersonRepository contactPersonRepository;
//
//    @Mock
//    private ContactPersonMapper contactPersonMapper;
//
//    @InjectMocks
//    private ContactPersonServiceImpl contactPersonServiceImpl;
//
//    @Test
//    @DisplayName("Testing findAllContactPerson() method - negative scenario (empty List)")
//    public void givenEmptyContactPersonList_whenFindAllContactPerson_thenReturnEmptyList() {
//        // given
//        given(contactPersonRepository.findAll()).willReturn(Collections.emptyList());
//
//        // when
//        List<ContactPersonDto> contactPersonsList = contactPersonServiceImpl.findAllContactPerson();
//
//        // then
//        assertTrue(contactPersonsList.isEmpty());
//    }
//
//    @Test
//    @DisplayName("Testing findAllContactPerson() method - positive scenario (valid input)")
//    public void givenContactPersonList_whenFindAllContactPerson_thenReturnContactPersonDtoObject() {
//        // given
//        given(contactPersonRepository.findAll()).willReturn(List.of(contactPerson));
//        given(contactPersonMapper.mapToContactPersonDto(contactPerson)).willReturn(contactPersonDto);
//
//        // when
//        List<ContactPersonDto> testingList = contactPersonServiceImpl.findAllContactPerson();
//
//        // then
//        assertAll(
//                () -> assertNotNull(testingList),
//                () -> assertEquals(1, testingList.size()),
//                () -> assertEquals(1L, testingList.get(0).getId()),
//                () -> assertEquals("test first name", testingList.get(0).getFirstName()),
//                () -> assertEquals("test last name", testingList.get(0).getLastName()),
//                () -> assertEquals("test@test.com", testingList.get(0).getEmail()),
//                () -> assertEquals("111-111-111", testingList.get(0).getPhoneNumber())
//        );
//    }
//
//    @Test
//    @DisplayName("Testing findContactPersonById() method - positive scenario (valid input).")
//    public void givenId_whenFindContactPersonById_thenReturnContactPersonDtoObject() {
//        // given
//        Long id = contactPerson.getId();
//        given(contactPersonRepository.findById(id)).willReturn(Optional.ofNullable(contactPerson));
//        given(contactPersonMapper.mapToContactPersonDto(contactPerson)).willReturn(contactPersonDto);
//
//        // when
//        ContactPersonDto testedContactPersonDto = contactPersonServiceImpl.findContactPersonById(id);
//
//        // then
//        assertAll(
//                () -> assertNotNull(testedContactPersonDto),
//                () -> assertEquals(1L, testedContactPersonDto.getId()),
//                () -> assertEquals("test first name", testedContactPersonDto.getFirstName()),
//                () -> assertEquals("test last name", testedContactPersonDto.getLastName()),
//                () -> assertEquals("test@test.com", testedContactPersonDto.getEmail()),
//                () -> assertEquals("111-111-111", testedContactPersonDto.getPhoneNumber())
//        );
//    }
//
//    @Test
//    @DisplayName("Testing findContactPersonById() method that throws ResourceNotFoundException.")
//    public void givenId_whenFindContactPersonById_thenThrowsResourceNotFoundException() {
//        // given
//        Long id = 2L;
//        given(contactPersonRepository.findById(id)).willReturn(Optional.empty());
//
//        // when, then
//        assertThrows(ResourceNotFoundException.class, () -> {
//            contactPersonServiceImpl.findContactPersonById(id);
//        });
//        verify(contactPersonRepository, times(1)).findById(id);
//        verify(contactPersonMapper, never()).mapToContactPersonDto(contactPerson);
//    }
//
//    @Test
//    @DisplayName("Testing findContactPersonByEmail() method - positive scenario (valid input).")
//    public void givenEmail_whenFindContactPersonByEmail_thenReturnContactPersonDtoObject() {
//        // given
//        String email = contactPerson.getEmail();
//        given(contactPersonRepository.findContactPersonByEmail(email)).willReturn(Optional.ofNullable(contactPerson));
//        given(contactPersonMapper.mapToContactPersonDto(contactPerson)).willReturn(contactPersonDto);
//
//        // when
//        ContactPersonDto testedContactPersonDto = contactPersonServiceImpl.findContactPersonByEmail(email);
//
//        // then
//        assertAll(
//                () -> assertNotNull(testedContactPersonDto),
//                () -> assertEquals(1L, testedContactPersonDto.getId()),
//                () -> assertEquals("test first name", testedContactPersonDto.getFirstName()),
//                () -> assertEquals("test last name", testedContactPersonDto.getLastName()),
//                () -> assertEquals("test@test.com", testedContactPersonDto.getEmail()),
//                () -> assertEquals("111-111-111", testedContactPersonDto.getPhoneNumber())
//        );
//    }
//
//    @Test
//    @DisplayName("Testing findContactPersonByEmail() method that throws ResourceNotFoundException.")
//    public void givenEmail_whenFindContactPersonByEmail_thenThrowsResourceNotFoundException() {
//        // given
//        String email = "fake email";
//        given(contactPersonRepository.findContactPersonByEmail(email)).willReturn(Optional.empty());
//
//        // when, then
//        assertThrows(ResourceNotFoundException.class, () -> {
//            contactPersonServiceImpl.findContactPersonByEmail(email);
//        });
//        verify(contactPersonRepository, times(1)).findContactPersonByEmail(email);
//        verify(contactPersonMapper, never()).mapToContactPersonDto(contactPerson);
//    }
//
//    @Test
//    @DisplayName("Testing createContactPerson() method - positive scenario (valid input).")
//    public void givenContactPersonWithValidEmail_whenCreateContactPerson_thenSaveNewContactPersonObject() {
//        // given
//        given(contactPersonRepository.findContactPersonByEmail(contactPerson.getEmail())).willReturn(Optional.empty());
//        given(contactPersonMapper.mapToContactPerson(contactPersonDto)).willReturn(contactPerson);
//        given(contactPersonRepository.save(contactPerson)).willReturn(contactPerson);
//        given(contactPersonMapper.mapToContactPersonDto(any())).willReturn(contactPersonDto);
//
//        // when
//        ContactPersonDto testedContactPersonDto = contactPersonServiceImpl.createContactPerson(contactPersonDto);
//
//        // then
//        assertAll(
//                () -> assertNotNull(testedContactPersonDto),
//                () -> assertEquals(1L, testedContactPersonDto.getId()),
//                () -> assertEquals("test first name", testedContactPersonDto.getFirstName()),
//                () -> assertEquals("test last name", testedContactPersonDto.getLastName()),
//                () -> assertEquals("test@test.com", testedContactPersonDto.getEmail()),
//                () -> assertEquals("111-111-111", testedContactPersonDto.getPhoneNumber())
//        );
//    }
//
//    @Test
//    @DisplayName("Testing updateContactPerson() method - positive scenario (valid input).")
//    public void givenContactPersonDtoObject_whenUpdateContactPerson_thenReturnUpdatedContactPersonObject() {
//        // given
//        ContactPerson updatedContactPerson = ContactPerson.builder()
//                .id(1L)
//                .firstName("updated first name")
//                .lastName("updated last name")
//                .email("updated@updated.com")
//                .phoneNumber("000-000-000")
//                .build();
//
//        ContactPersonDto updatedContactPersonDto = ContactPersonDto.builder()
//                .id(1L)
//                .firstName("updated first name")
//                .lastName("updated last name")
//                .email("updated@updated.com")
//                .phoneNumber("000-000-000")
//                .build();
//
//        given(contactPersonRepository.findById(contactPersonDto.getId())).willReturn(Optional.ofNullable(contactPerson));
//        given(contactPersonRepository.save(contactPerson)).willReturn(updatedContactPerson);
//        given(contactPersonMapper.mapToContactPersonDto(updatedContactPerson)).willReturn(updatedContactPersonDto);
//
//        // when
//        ContactPersonDto testedContactPersonDto = contactPersonServiceImpl.updateContactPerson(contactPersonDto, 1L);
//
//        // then
//        assertAll(
//                () -> assertNotNull(testedContactPersonDto),
//                () -> assertEquals("updated first name", testedContactPersonDto.getFirstName()),
//                () -> assertEquals("updated last name", testedContactPersonDto.getLastName()),
//                () -> assertEquals("updated@updated.com", testedContactPersonDto.getEmail()),
//                () -> assertEquals("000-000-000", testedContactPersonDto.getPhoneNumber())
//        );
//    }
//
//    @Test
//    @DisplayName("Testing deleteContactPersonById() method - positive scenario (valid input).")
//    public void givenId_whenDeleteContactPersonById_thenContactPersonObjectDeleted() {
//        // given
//        Long id = 1L;
//        given(contactPersonRepository.findById(id)).willReturn(Optional.ofNullable(contactPerson));
//
//        // when
//        contactPersonServiceImpl.deleteContactPersonById(id);
//
//        // then
//        verify(contactPersonRepository, times(1)).findById(id);
//        verify(contactPersonRepository, times(1)).deleteById(id);
//    }
//
//}
