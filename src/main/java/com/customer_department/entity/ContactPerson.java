package com.customer_department.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTACT_PERSON")
public class ContactPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTACT_PERSON_F_NAME", nullable = false)
    private String contactPersonFirstName;

    @Column(name = "CONTACT_PERSON_L_NAME", nullable = false)
    private String contactPersonLastName;

    @Column(name = "CONTACT_PERSON_EMAIL", nullable = false, unique = true)
    private String contactPersonEmail;

    @Column(name = "CONTACT_PERSON_PHONE", nullable = false)
    private String contactPersonPhoneNumber;

    @ManyToOne
    @JoinColumn(name = "FK_CUSTOMER")
    private Customer customer;

}
