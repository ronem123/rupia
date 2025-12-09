package com.ram.rupia.rupia.service.otp;


import com.ram.rupia.rupia.config.OtpMapper;
import com.ram.rupia.rupia.dto.CustomerDTO;
import com.ram.rupia.rupia.dto.OtpDTO;
import com.ram.rupia.rupia.entity.Customer;
import com.ram.rupia.rupia.entity.Otp;
import com.ram.rupia.rupia.entity.Transaction;
import com.ram.rupia.rupia.enums.OtpType;
import com.ram.rupia.rupia.exception.BadRequestException;
import com.ram.rupia.rupia.post_request.VerifyOtpRequest;
import com.ram.rupia.rupia.repository.CustomerRepository;
import com.ram.rupia.rupia.repository.OtpRepository;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Ram Mandal on 03/12/2025
 *
 * @System: Apple M1 Pro
 */
@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final CustomerRepository customerRepository;
    private final OtpRepository otpRepository;
    private final OtpMapper otpMapper;

    @Override
    public OtpDTO generateOtp(Long customerId, OtpType otpType) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Sorry! customer not found"));
        String otp = String.format("%06d", new Random().nextInt(999999));
        String otpRef = UUID.randomUUID().toString();
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

        Otp otpEntity = Otp.builder()
                .customer(customer)
                .otp(otp)
                .otpRefNo(otpRef)
                .otpType(otpType)
                .expiryTime(expiryTime)
                .isOtpUsed(false)
                .build();


        return otpMapper.toOtpDto(otpRepository.save(otpEntity));
    }

    @Transactional
    @Override
    public Boolean verifyOtp(VerifyOtpRequest body) {
        //check otp with all the provided parameters, so that it will be more secure
        Otp otp = otpRepository.findByOtpAndOtpRefNoAndOtpTypeAndIsOtpUsedFalse(body.getOtp(), body.getOtpRef(), body.getOtpType()).orElseThrow(
                () -> new IllegalArgumentException("Sorry! Otp Not found"));

        //reject or throw an exception if the provided customer ID and the customer bound with the otp do not match
        if (!otp.getCustomer().getId().equals(body.getCustomerId())) {
            throw new BadRequestException("Sorry Customer not matched");
        }
        //check if the provided otp has expired
        if (otp.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        //return false if the provided opt does not match
        //rare case
        if (!body.getOtp().equals(otp.getOtp())) {
            throw new BadRequestException("Incorrect OTP");
        }

        //update otp used status
        otp.setOtpUsed(true);

        return true;
    }

    @Override
    public Otp getOtp(String otpRef, OtpType otpType) {
        return otpRepository.findByOtpRefNoAndOtpType(otpRef, otpType).orElseThrow(() -> new IllegalArgumentException("No Otp Found"));
    }
}
