package com.ram.rupia.rupia;

import com.ram.rupia.rupia.config.CustomerMapper;
import com.ram.rupia.rupia.config.WalletMapper;
import com.ram.rupia.rupia.dto.CustomerDTO;
import com.ram.rupia.rupia.dto.CustomerWithWalletDTO;
import com.ram.rupia.rupia.dto.OtpDTO;
import com.ram.rupia.rupia.dto.TransactionDTO;
import com.ram.rupia.rupia.entity.Customer;
import com.ram.rupia.rupia.entity.Wallet;
import com.ram.rupia.rupia.enums.Gender;
import com.ram.rupia.rupia.post_request.CustomerRequestBody;
import com.ram.rupia.rupia.post_request.VerifyOtpRequest;
import com.ram.rupia.rupia.post_request.WalletReloadRequest;
import com.ram.rupia.rupia.post_request.WalletRequestBody;
import com.ram.rupia.rupia.repository.CustomerRepository;
import com.ram.rupia.rupia.repository.WalletRepository;
import com.ram.rupia.rupia.response.CustomerTransactionResponse;
import com.ram.rupia.rupia.service.customer.CustomerServiceImpl;
import com.ram.rupia.rupia.service.otp.OtpServiceImpl;
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
    private CustomerServiceImpl customerService;

    @Autowired
    private WalletServiceImpl walletService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private WalletMapper walletMapper;


    @Test
    void customerRepositoryTest() {
        List<CustomerDTO> customerDTOS = customerService.getCustomers();
        for (CustomerDTO dto : customerDTOS) {
            System.out.print("Customer : " + dto);
        }
    }

    @Test
    void registerNewCustomerTest() {
        CustomerRequestBody body = CustomerRequestBody.builder()
                .name("Kusum Mandal")
                .gender(Gender.FEMALE)
                .email("kusum@gmail.com")
                .contact("9845454334")
                .address("Biratnagar 16, Nepal")
                .idNumber("55555")
                .birthDate(LocalDate.of(1995, 2, 11))
                .build();

        customerService.createNewCustomer(body);
    }

    @Test
    void approveCustomerAndCreateWalletTest() {
        //We are going to add a new wallet to the customer: 1
        boolean approved = customerService.approveCustomerRegistration(7L);
        System.out.println("Customer approved and wallet created : " + approved);
    }

    @Test
    void updateCustomerTest() {
        CustomerRequestBody body = CustomerRequestBody.builder().gender(Gender.FEMALE).build();
        CustomerDTO dto = customerService.updateCustomer(5L, body);
        System.out.println("Updated Customer " + dto);
    }

    @Test
    void customerWithWalletBalance() {
        List<CustomerWithWalletDTO> list = customerService.getCustomerWithWalletInfo(1l);
        for (CustomerWithWalletDTO dto : list) {
            System.out.println("Found user with " + dto);
        }
    }

    @Test
    void walletReloadTest() {
        WalletReloadRequest body = WalletReloadRequest.builder()
                .amount(BigDecimal.valueOf(1000))
                .remarks("Self wallet reload")
                .walletId(2L)
                .build();
        TransactionDTO dto = walletService.reloadWallet(body);
        System.out.println(dto);
    }

    @Test
    void getCustomerTransaction() {
        List<CustomerTransactionResponse> list = walletService.getCustomerTransaction();
        for (CustomerTransactionResponse s : list) {
            System.out.println("Information: " + s.toString());
        }
    }

    @Test
    void loginUserTest() {
        OtpDTO dto = customerService.loginUser("9808065961");
        System.out.println("OTP response" + dto);
    }

    @Test
    void OtpVerificationTest() {
        VerifyOtpRequest request = new VerifyOtpRequest("752559", "e970f016-9595-4ee0-9a77-6e90458c628e");
        CustomerDTO dto = customerService.verifyOtp(request);
        System.out.println("OTPVerify :" + dto);
    }
}
