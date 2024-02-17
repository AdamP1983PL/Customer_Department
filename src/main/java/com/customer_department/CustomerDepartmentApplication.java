package com.customer_department;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:database.properties")
public class CustomerDepartmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerDepartmentApplication.class, args);
    }

}
