package com.ram.rupia.rupia.service.customer;

import com.ram.rupia.rupia.dto.CustomerDTO;
import com.ram.rupia.rupia.dto.CustomerWithWalletDTO;
import com.ram.rupia.rupia.dto.OtpDTO;
import com.ram.rupia.rupia.enums.OtpType;
import com.ram.rupia.rupia.post_request.CustomerLoginRequestBody;
import com.ram.rupia.rupia.post_request.CustomerRequestBody;
import com.ram.rupia.rupia.post_request.VerifyOtpRequest;

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
    Boolean approveCustomerRegistration(Long userId);

    CustomerDTO updateCustomer(Long customerId, CustomerRequestBody body);

    List<CustomerWithWalletDTO> getCustomerWithWalletInfo(Long customerId);

    OtpDTO loginUser(String mobileNumber);

    CustomerDTO verifyOtp(VerifyOtpRequest request);

}
