package com.ram.rupia.rupia.repository;


import com.ram.rupia.rupia.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Ram Mandal on 03/12/2025
 *
 * @System: Apple M1 Pro
 */
public interface OtpRepository extends JpaRepository<Otp, Long> {

    //jpa method to get OTP row
    //if otp ref is present and is_otp_used is false
    Optional<Otp> findByOtpAndOtpRefNoAndIsOtpUsedFalse(String otp, String otpRef);

    Optional<Otp> findByOtpRefNo(String otpRef);
}
