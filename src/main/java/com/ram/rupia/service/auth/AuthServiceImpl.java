package com.ram.rupia.service.auth;


import com.ram.rupia.config.CustomerMapper;
import com.ram.rupia.dto.CustomerDTO;
import com.ram.rupia.dto.OtpDTO;
import com.ram.rupia.entity.Customer;
import com.ram.rupia.entity.Otp;
import com.ram.rupia.enums.OtpType;
import com.ram.rupia.post_request.VerifyOtpRequest;
import com.ram.rupia.repository.CustomerRepository;
import com.ram.rupia.service.otp.OtpServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by Ram Mandal on 09/12/2025
 *
 * @System: Apple M1 Pro
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CustomerRepository customerRepository;
    private final OtpServiceImpl otpService;
    private final CustomerMapper customerMapper;

    @Override
    public OtpDTO loginUser(String mobileNumber) {
        /*
         * 1. Check if the customer is present with the provided mobile number
         * 2. If present, insert new otp and otp ref to otp table alongside with customer ID
         * 3. Send OTP via sms
         */
        Customer customer = customerRepository.findByContact(mobileNumber).orElseThrow(
                () -> new IllegalArgumentException("Sorry ! customer not found"));

        return otpService.generateOtp(customer.getId(), OtpType.LOGIN);
    }

    @Override
    public OtpDTO resendOtp(String otpRef, OtpType otpType) {
        Otp oldOtp = otpService.getOtp(otpRef, otpType);

        //mark previous otp as expired/used
        oldOtp.setOtpUsed(true);

        //generate new otp
        return otpService.generateOtp(oldOtp.getCustomer().getId(), otpType);
    }

    @Override
    public CustomerDTO verifyLoginOtp(VerifyOtpRequest request) {
        boolean isOtpValid = otpService.verifyOtp(request);
        if (!isOtpValid) {
            throw new RuntimeException("Invalid OTP");
        } else {
            Otp otp = otpService.getOtp(request.getOtpRef(), request.getOtpType());
            Customer customer = otp.getCustomer();
            return customerMapper.toCustomerDTO(customer);
        }
    }
}
