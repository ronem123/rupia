package com.ram.rupia.service.auth;


import com.ram.rupia.api.dto.CustomerDTO;
import com.ram.rupia.api.dto.OtpDTO;
import com.ram.rupia.domain.enums.OtpType;
import com.ram.rupia.api.post_request.VerifyOtpRequest;

/**
 * Created by Ram Mandal on 09/12/2025
 *
 * @System: Apple M1 Pro
 */
public interface AuthService {
    OtpDTO loginUser(String mobileNumber);

    OtpDTO resendOtp(String otpRef, OtpType otpType);

    CustomerDTO verifyLoginOtp(VerifyOtpRequest request);
}
