package com.ram.rupia.rupia.service.customer;

import com.ram.rupia.rupia.dto.CustomerDTO;
import com.ram.rupia.rupia.dto.CustomerWithWalletDTO;
import com.ram.rupia.rupia.post_request.CustomerRequestBody;

import java.util.List;

/**
 * Created by Ram Mandal on 16/11/2025
 *
 * @System: Apple M1 Pro
 */

public interface CustomerService {
    List<CustomerDTO> getCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createNewCustomer(CustomerRequestBody requestBody);

    void deleteCustomer(Long id);

    //method consumed by BO
    CustomerDTO approveCustomerRegistration(Long userId);

    CustomerDTO updateCustomer(Long customerId, CustomerRequestBody body);

    List<CustomerWithWalletDTO> getCustomerWithWalletInfo(Long customerId);

}
