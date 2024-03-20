package com.customer_department.controller;

import com.customer_department.service.customer.CustomerServiceImpl;
import com.customer_department.service.customer.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/customers")
public class CustomerThymeleafController {

    private final CustomerServiceImpl customerServiceImpl;

    public CustomerThymeleafController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @GetMapping("/")
    public String listAllCustomers(Model model) {
        List<CustomerDto> customerDtoList = customerServiceImpl.findAllCustomers();
        model.addAttribute("customerDtoList", customerDtoList);
        log.info("====>>>> listAllCustomers() execution.");
        return "customers-list";
    }

}













