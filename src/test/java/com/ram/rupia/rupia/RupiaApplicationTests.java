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
import com.ram.rupia.rupia.enums.OtpType;
import com.ram.rupia.rupia.post_request.*;
import com.ram.rupia.rupia.repository.CustomerRepository;
import com.ram.rupia.rupia.repository.WalletRepository;
import com.ram.rupia.rupia.response.CustomerTransactionResponse;
import com.ram.rupia.rupia.response.InitiateTransactionResponse;
import com.ram.rupia.rupia.service.auth.AuthServiceImpl;
import com.ram.rupia.rupia.service.customer.CustomerServiceImpl;
import com.ram.rupia.rupia.service.otp.OtpServiceImpl;
import com.ram.rupia.rupia.service.transaction.TransactionServiceImpl;
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

    @Autowired
    private TransactionServiceImpl transactionService;

    @Autowired
    private AuthServiceImpl authService;


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
                .name("Ram Mandal")
                .gender(Gender.MALE)
                .email("ram@gmail.com")
                .contact("9808065961")
                .address("Biratnagar 16, Nepal")
                .idNumber("55555")
                .birthDate(LocalDate.of(1995, 2, 11))
                .build();

        customerService.createNewCustomer(body);
    }

    @Test
    void approveCustomerAndCreateWalletTest() {
        //We are going to add a new wallet to the customer: 1
        CustomerDTO customerDTO = customerService.approveCustomerRegistration(1L);
        System.out.println("Customer approved and wallet created : " + customerDTO);
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
                .walletId(1L)
                .build();
        InitiateTransactionResponse response = transactionService.loadMoneyToWallet(body);
        System.out.println(response);
    }

    @Test
    void getCustomerTransaction() {
        List<CustomerTransactionResponse> list = transactionService.getCustomerTransaction();
        for (CustomerTransactionResponse s : list) {
            System.out.println("Information: " + s.toString());
        }
    }

    @Test
    void loginUserTest() {
        OtpDTO dto = authService.loginUser("9845454334");
        System.out.println("OTP response" + dto);
    }

    @Test
    void OtpVerificationForLoginTest() {
        //Verify Otp for login
        VerifyOtpRequest request = new VerifyOtpRequest("874781", "ee037317-13ef-4e94-a7d8-4c04f1f10503", 1L, OtpType.LOGIN);
        CustomerDTO dto = authService.verifyLoginOtp(request);
        System.out.println("OTPVerify :" + dto);
    }

    @Test
    void OtpVerificationForTransactionTest() {
        //Verify Otp for Transaction
//        VerifyOtpRequest request = new VerifyOtpRequest("040649", "ae99c7e2-0ebb-4a15-b69d-7f3bc25cd33b", 1L, OtpType.TRANSACTION);
        ConfirmTransactionRequest request = new ConfirmTransactionRequest("488488", "786408a1-d6b7-4179-94f4-355c3640c900", "97ce38a0-1b9a-4d55-b9c7-24964ef98f6f", 1L);
        TransactionDTO dto = transactionService.confirmTransaction(request);
        System.out.println("OTPVerify :" + dto);
    }

    @Test
    void resendOtpForLoginTest() {
        OtpDTO dto = authService.resendOtp("ee037317-13ef-4e94-a7d8-4c04f1f10503", OtpType.LOGIN);
        System.out.println("Resend: " + dto);
    }

    @Test
    void resendOtpForTransactionTest() {
        OtpDTO dto = transactionService.resendOtp("ae99c7e2-0ebb-4a15-b69d-7f3bc25cd33b", OtpType.TRANSACTION);
        System.out.println("Resend: " + dto);
    }
}
