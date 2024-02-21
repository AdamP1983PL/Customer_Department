package com.customer_department.service.contact_person.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactPersonDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Long customerId;

}

// todo add validation
// todo add swagger documentation
