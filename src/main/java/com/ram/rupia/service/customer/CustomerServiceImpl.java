package com.ram.rupia.service.customer;

import com.ram.rupia.config.CustomerMapper;
import com.ram.rupia.dto.CustomerDTO;
import com.ram.rupia.dto.CustomerWithWalletDTO;
import com.ram.rupia.entity.Customer;
import com.ram.rupia.enums.CustomerStatus;
import com.ram.rupia.post_request.CustomerRequestBody;
import com.ram.rupia.repository.CustomerRepository;
import com.ram.rupia.service.otp.OtpService;
import com.ram.rupia.service.wallet.WalletServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    private final CustomerMapper customerMapper;

    private final WalletServiceImpl walletService;


    @Override
    public List<CustomerDTO> getCustomers() {
        List<Customer> customers = customerRepository.findAll();

//        return customers
//                .stream()
////                .map((customer) -> customerMapper.toCustomerDTO(customer))
//                .map(customerMapper::toCustomerDTO)
//                .toList();

        return customerMapper.toCustomersDTO(customers);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Sorry customer with " + id + " Not found"));
        return customerMapper.toCustomerDTO(customer);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerRequestBody requestBody) {
        //create a new entity required by the repository for saving a new customer
        Customer newCustomer = customerMapper.toCustomer(requestBody);

        //return customer after saving the new customer in DB
        Customer customer = customerRepository.save(newCustomer);

        //return the Customer DTO as required on the return type
        return customerMapper.toCustomerDTO(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Sorry! but customer with " + id + " does not exist");
        }
        customerRepository.deleteById(id);
    }

    @Transactional
    @Override
    public CustomerDTO approveCustomerRegistration(Long userId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("Sorry ! but user not found"));

        walletService.createNewWallet(customer);
        /*
         * We do not need to explicitly save the customer, as we are under the @Transactional state
         * whenever any update happens to the customer, dirty checking will be there, and if any changes
         * It will automatically update it.
         * Note: since we are working on the same customer object. So, no new row will be inserted. JPA will manage it
         * automatically. like this
         * UPDATE customer_tbl
         * SET customer_status = 'ACTIVE'
         * WHERE id = <customer_id>;
         */

        if (customer.getStatus() == CustomerStatus.KYC_PENDING) {
            customer.setStatus(CustomerStatus.ACTIVE);
        }
        return customerMapper.toCustomerDTO(customer);

    }

    @Transactional
    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerRequestBody body) {
        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(()
                -> new RuntimeException("Sorry ! customer not found"));
        customerMapper.updateFromRequest(body, existingCustomer);

        return customerMapper.toCustomerDTO(existingCustomer);
    }

    @Override
    public List<CustomerWithWalletDTO> getCustomerWithWalletInfo(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                new RuntimeException("Sorry! but no customer found with id " + customerId));
        return customerRepository.getCustomerWithWallet(customerId);
    }
}
