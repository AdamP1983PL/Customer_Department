package com.customer_department.service.customer;

import com.customer_department.exceptions.ResourceNotFoundException;
import com.customer_department.exceptions.TaxNumberAlreadyExistsException;
import com.customer_department.model.contact_person.domain.ContactPerson;
import com.customer_department.model.contact_person.repository.ContactPersonRepository;
import com.customer_department.model.customer.domain.Customer;
import com.customer_department.model.customer.repository.CustomerRepository;
import com.customer_department.service.customer.dto.CustomerDto;
import com.customer_department.service.customer.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private ContactPersonRepository contactPersonRepository;
    private CustomerMapper customerMapper;
    private EntityManager entityManager;

    @Override
    public List<CustomerDto> findAllCustomers() {
        log.info("====>>>> findAllCustomers() execution.");
        return customerRepository.findAll().stream()
                .map(customerMapper::mapToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        log.info("====>>>> findCustomerById(\"" + id + "\") execution.");
        return customerMapper.mapToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> findCustomersByName(String name) {
        List<Customer> customers = customerRepository.findCustomerByCustomerNameContaining(name);

        log.info("====>>>> findCustomersByName(\"" + name + "\") execution.");
        return customers.stream()
                .map(customerMapper::mapToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Optional<Customer> customer = customerRepository.findCustomerByTaxNumber(customerDto.getTaxNumber());

        if (customer.isPresent()) {
            throw new TaxNumberAlreadyExistsException("Tax number", customerDto.getTaxNumber());
        }

//        /*2024-02-24 20:08:46.516 ERROR 7344 --- [nio-8080-exec-3] o.a.c.c.C.[.[.[/].[dispatcherServlet]
//        : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception
//        [Request processing failed; nested exception is org.springframework.dao.InvalidDataAccessApiUsageException:
//        detached entity passed to persist: com.customer_department.model.contact_person.domain.ContactPerson;
//        nested exception is org.hibernate.PersistentObjectException: detached entity passed to persist:
//        com.customer_department.model.contact_person.domain.ContactPerson]*/

        List<ContactPerson> managedContactPersons = customerDto.getContactPersonsId().stream()
                .map(id -> contactPersonRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Contact Person", "id", id)))
                .collect(Collectors.toList());

        customerDto.setContactPersonsId(managedContactPersons.stream()
                .map(ContactPerson::getId)
                .collect(Collectors.toList()));

        Customer customerToBeSaved = customerMapper.mapToCustomer(customerDto);

        customerToBeSaved.setContactPersons(managedContactPersons);
        Customer savedCustomer = customerRepository.save(customerToBeSaved);

        log.info("====>>>> createCustomer(" + customerDto.getCustomerName() + ") execution.");
        return customerMapper.mapToCustomerDto(savedCustomer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Long id) {
        Customer customer = customerRepository.findById(id)
                .map(cust -> {
                    cust.setCustomerName(customerDto.getCustomerName());
                    cust.setTaxNumber(customerDto.getTaxNumber());
                    cust.setCountry(customerDto.getCountry());
                    cust.setCity(customerDto.getCity());
                    cust.setPostalCode(customerDto.getPostalCode());
                    cust.setStreet(customerDto.getStreet());
                    cust.setCustomerEmail(customerDto.getCustomerEmail());
                    cust.setCustomerPhoneNumber(customerDto.getCustomerPhoneNumber());
                    cust.setCustomerWebsite(customerDto.getCustomerWebsite());
                    cust.setActive(customerDto.isActive());
                    cust.setPaymentIsBlocked(customerDto.isPaymentIsBlocked());
                    cust.setTaxValue(customerDto.getTaxValue());
                    return customerRepository.save(cust);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        log.info("====>>>> updateCustomer() execution.");
        return customerMapper.mapToCustomerDto(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        customerRepository.deleteById(id);
        log.info("====>>>> Customer with id " + id + " deleted successfully.");
    }

}
