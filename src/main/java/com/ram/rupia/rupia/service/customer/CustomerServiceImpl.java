package com.ram.rupia.rupia.service.customer;

import com.ram.rupia.rupia.dto.CustomerDTO;
import com.ram.rupia.rupia.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getName(), customer.getContact())).toList();
    }
}
