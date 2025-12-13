package com.ram.rupia.service.otp;


import com.ram.rupia.dto.OtpDTO;
import com.ram.rupia.entity.Otp;
import com.ram.rupia.entity.Transaction;
import com.ram.rupia.enums.OtpType;
import com.ram.rupia.post_request.VerifyOtpRequest;

/**
 * Created by Ram Mandal on 03/12/2025
 *
 * @System: Apple M1 Pro
 */
public interface OtpService {
    OtpDTO generateOtp(Long customerId, OtpType otpType);

    Boolean verifyOtp(VerifyOtpRequest body);


    Otp getOtp(String otpRef, OtpType otpType);
}
