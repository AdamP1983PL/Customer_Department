package com.customer_department;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:database.properties")
//Registering CustomerDepartment Service as a Eureka Client with the Eureka Server
@EnableEurekaClient
@EnableFeignClients
public class CustomerDepartmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerDepartmentApplication.class, args);
    }

}


