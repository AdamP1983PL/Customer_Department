package com.customer_department.controller;

import com.customer_department.service.customer.CustomerServiceImpl;
import com.customer_department.service.customer.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class CustomerThymeleafController {

    private final CustomerServiceImpl customerServiceImpl;

    public CustomerThymeleafController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @GetMapping("/customers")
    public String listAllCustomers(Model model) {
        List<CustomerDto> customerDtoList = customerServiceImpl.findAllCustomers();
        model.addAttribute("customerDtoList", customerDtoList);
        log.info("====>>>> listAllCustomers() execution.");
        return "customers-list";
    }

    @GetMapping("/customers/add-customer")
    public String addCustomer(Model model) {
        CustomerDto customerDto = new CustomerDto();
        model.addAttribute("customerDto", customerDto);
        log.info("====>>>> addCustomer() execution");
        return "add-new-customer";
    }

    @PostMapping("/customers/save-new-customer")
    public String saveNewCustomer(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customerDto", customerDto);
            return "add-new-customer";
        }
        customerServiceImpl.createCustomer(customerDto);
        log.info("====>>>> saveNewCustomer() execution");
        return "redirect:/customers";
    }

    @GetMapping("/customers/details/{id}")
    public String listCustomerDetails(@PathVariable("id") Long id, Model model) {
        CustomerDto customerDetailsDto = customerServiceImpl.findCustomerById(id);
        model.addAttribute("customerDetailsDto", customerDetailsDto);
        log.info("====>>>> listCustomerDetails(id: " + id + ") execution");
        return "customer-details";
    }

    @GetMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable("id") Long id, Model model) {
        CustomerDto customerDto = customerServiceImpl.findCustomerById(id);
        model.addAttribute("customerDto", customerDto);
        return "edit-customer";
    }

    @PostMapping("/customers/update/{id}")
    public String updateCustomer(@PathVariable("id") Long id,
                                 @Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customerDto", customerDto);
            return "edit-customer";
        }
        customerDto.setId(id);
        customerServiceImpl.mvcUpdateCustomer(customerDto);
        log.info("====>>>> updateCustomer(" + customerDto + ") execution");
        return "redirect:/customers";
    }

    @GetMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerServiceImpl.deleteCustomerById(id);
        log.info("====>>>> deleteCustomer(id: " + id + ") execution.");
        return "redirect:/customers";
    }

}
