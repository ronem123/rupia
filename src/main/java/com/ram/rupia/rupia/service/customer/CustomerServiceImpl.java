package com.ram.rupia.rupia.service.customer;

import com.ram.rupia.rupia.dto.CustomerDTO;
import com.ram.rupia.rupia.entity.Customer;
import com.ram.rupia.rupia.post_request.CustomerRequestBody;
import com.ram.rupia.rupia.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ram Mandal on 16/11/2025
 *
 * @System: Apple M1 Pro
 */

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CustomerDTO> getCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers
                .stream()
                .map((customer) -> modelMapper.map(customer, CustomerDTO.class))
                .toList();
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Sorry customer with " + id + " Not found"));
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerRequestBody requestBody) {
        //create a new entity required by the repository for saving a new customer
        Customer newCustomer = modelMapper.map(requestBody, Customer.class);

        //return customer after saving the new customer in DB
        Customer customer = customerRepository.save(newCustomer);

        //return the Customer DTO as required on the return type
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Sorry! but customer with " + id + " does not exist");
        }
        customerRepository.deleteById(id);
    }
}
