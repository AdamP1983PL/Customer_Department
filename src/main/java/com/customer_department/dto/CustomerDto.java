package com.customer_department.dto;

import com.customer_department.entity.ContactPerson;
import com.customer_department.enums.PaymentMethod;
import com.customer_department.enums.TaxValue;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private Long id;
    private String customerName;
    private String taxNumber;
    private String country;
    private String city;
    private String postalCode;
    private String street;
    private String customerEmail;
    private String customerPhoneNumber;
    private String customerWebsite;
    private boolean isActive;
    private boolean paymentIsBlocked;
    private PaymentMethod paymentMethod;
    private TaxValue taxValue;
    private List<ContactPerson> contactPersons;

}
