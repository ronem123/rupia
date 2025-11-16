package com.ram.rupia.rupia.service.customer;

import com.ram.rupia.rupia.dto.CustomerDTO;

import java.util.List;

/**
 * Created by Ram Mandal on 16/11/2025
 *
 * @System: Apple M1 Pro
 */

public interface CustomerService {
    List<CustomerDTO> getCustomers();

    CustomerDTO getCustomerById(Long id);
}
