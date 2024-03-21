package com.customer_department.model.customer.domain;

import com.customer_department.model.enums.PaymentMethod;
import com.customer_department.model.enums.TaxValue;
import lombok.Builder;

import javax.persistence.*;

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
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Column(name = "TAX_VALUE")
    @Enumerated(EnumType.STRING)
    private TaxValue taxValue;
    @Column(name = "CONTACT_PERSON_NAME", nullable = false)
    private String contactPersonName;
    @Column(name = "CONTACT_PERSON_EMAIL", nullable = false)
    private String contactPersonEmail;
    @Column(name = "CONTACT_PERSON_PHONE", nullable = false)
    private String contactPersonPhone;

    public Customer() {
    }

    public Customer(Long id,
                    String customerName,
                    String taxNumber,
                    String country,
                    String city,
                    String postalCode,
                    String street,
                    String customerEmail,
                    String customerPhoneNumber,
                    String customerWebsite,
                    boolean isActive,
                    boolean paymentIsBlocked,
                    PaymentMethod paymentMethod,
                    TaxValue taxValue,
                    String contactPersonName,
                    String contactPersonEmail,
                    String contactPersonPhone) {
        this.id = id;
        this.customerName = customerName;
        this.taxNumber = taxNumber;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerWebsite = customerWebsite;
        this.isActive = isActive;
        this.paymentIsBlocked = paymentIsBlocked;
        this.paymentMethod = paymentMethod;
        this.taxValue = taxValue;
        this.contactPersonName = contactPersonName;
        this.contactPersonEmail = contactPersonEmail;
        this.contactPersonPhone = contactPersonPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerWebsite() {
        return customerWebsite;
    }

    public void setCustomerWebsite(String customerWebsite) {
        this.customerWebsite = customerWebsite;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isPaymentIsBlocked() {
        return paymentIsBlocked;
    }

    public void setPaymentIsBlocked(boolean paymentIsBlocked) {
        this.paymentIsBlocked = paymentIsBlocked;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TaxValue getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(TaxValue taxValue) {
        this.taxValue = taxValue;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", taxNumber='" + taxNumber + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", street='" + street + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", customerWebsite='" + customerWebsite + '\'' +
                ", isActive=" + isActive +
                ", paymentIsBlocked=" + paymentIsBlocked +
                ", paymentMethod=" + paymentMethod +
                ", taxValue=" + taxValue +
                ", contactPersonName='" + contactPersonName + '\'' +
                ", contactPersonEmail='" + contactPersonEmail + '\'' +
                ", contactPersonPhone='" + contactPersonPhone + '\'' +
                '}';
    }

}
