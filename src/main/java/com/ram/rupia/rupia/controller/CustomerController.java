package com.ram.rupia.rupia.controller;

import com.ram.rupia.rupia.dto.CustomerDTO;
import com.ram.rupia.rupia.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("/customer")
    public List<CustomerDTO> getListCustomers() {
        return customerService.getCustomers();
    }

}
