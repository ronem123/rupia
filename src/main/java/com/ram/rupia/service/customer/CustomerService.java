package com.ram.rupia.service.customer;

import com.ram.rupia.api.dto.CustomerDTO;
import com.ram.rupia.api.dto.CustomerWithWalletDTO;
import com.ram.rupia.api.post_request.CustomerRequestBody;

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

    CustomerDTO updateCustomer(Long customerId, CustomerRequestBody body);

    List<CustomerWithWalletDTO> getCustomerWithWalletInfo(Long customerId);

}
