package com.customer_department.repository;

import com.customer_department.entity.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactPersonRepository extends JpaRepository<ContactPerson, Long> {

    Optional<ContactPerson> findContactPersonByEmail(String email);

}
