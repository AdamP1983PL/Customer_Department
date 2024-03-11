package com.customer_department.model.customer.domain;

import com.customer_department.model.enums.PaymentMethod;
import com.customer_department.model.enums.TaxValue;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CUSTOMERS")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String customerName;
    @Column(name = "TAX_NUMBER", nullable = false)
    private String taxNumber;
    @Column(name = "COUNTRY", nullable = false)
    private String country;
    @Column(name = "CITY", nullable = false)
    private String city;
    @Column(name = "POSTAL_CODE", nullable = false)
    private String postalCode;
    @Column(name = "STREET", nullable = false)
    private String street;
    @Column(name = "CUSTOMER_EMAIL", nullable = false)
    private String customerEmail;
    @Column(name = "CUSTOMER_PHONE_NO", nullable = false)
    private String customerPhoneNumber;
    @Column(name = "CUSTOMER_WEBSITE")
    private String customerWebsite;
    @Column(name = "ACTIVE_CLIENT")
    private boolean isActive;
    @Column(name = "BLOCKED_PAYMENT")
    private boolean paymentIsBlocked;
    @Column(name = "PAYMENT_METHOD")
    private PaymentMethod paymentMethod;
    @Column(name = "TAX_VALUE")
    private TaxValue taxValue;
    @Column(name = "CONTACT_PERSON_NAME", nullable = false)
    private String contactPersonName;
    @Column(name = "CONTACT_PERSON_EMAIL", nullable = false)
    private String contactPersonEmail;
    @Column(name = "CONTACT_PERSON_PHONE", nullable = false)
    private String contactPersonPhone;

}
