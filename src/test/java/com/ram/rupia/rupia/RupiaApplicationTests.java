package com.ram.rupia.rupia;

import com.ram.rupia.rupia.config.CustomerMapper;
import com.ram.rupia.rupia.config.WalletMapper;
import com.ram.rupia.rupia.dto.CustomerDTO;
import com.ram.rupia.rupia.entity.Customer;
import com.ram.rupia.rupia.entity.Wallet;
import com.ram.rupia.rupia.enums.Gender;
import com.ram.rupia.rupia.post_request.CustomerRequestBody;
import com.ram.rupia.rupia.post_request.WalletRequestBody;
import com.ram.rupia.rupia.repository.CustomerRepository;
import com.ram.rupia.rupia.repository.WalletRepository;
import com.ram.rupia.rupia.service.wallet.WalletServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class RupiaApplicationTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WalletServiceImpl walletService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private WalletMapper walletMapper;

    @Test
    void customerRepositoryTest() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = customerMapper.toCustomersDTO(customers);
        for (CustomerDTO dto : customerDTOS) {
            System.out.print("Customer : " + dto);
        }
    }

    @Test
    void registerNewCustomerTest() {
        CustomerRequestBody body = CustomerRequestBody.builder()
                .name("Saanvi Mandal")
                .gender(Gender.MALE)
                .email("sanvi.mandal@gmail.com")
                .contact("9812322343")
                .address("Biratnagar 16, Nepal")
                .idNumber("654321")
                .birthDate(LocalDate.of(2025, 2, 11))
                .build();
        Customer customer = customerMapper.toCustomer(body);

        customerRepository.save(customer);
    }

    @Test
    void createWalletTest() {
        //We are going to add a new wallet to the customer: 1
        WalletRequestBody body = WalletRequestBody.builder()
                .customerId(1L)
                .walletBalance(BigDecimal.valueOf(2000.00))
                .walletSize(2000)
                .build();

        walletService.createWallet(body);
    }

}
