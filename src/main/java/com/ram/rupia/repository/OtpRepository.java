package com.ram.rupia.repository;


import com.ram.rupia.domain.entity.Otp;
import com.ram.rupia.domain.enums.OtpType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by Ram Mandal on 03/12/2025
 *
 * @System: Apple M1 Pro
 */
public interface OtpRepository extends JpaRepository<Otp, Long> {

    //jpa method to get OTP row
    //if otp ref is present and is_otp_used is false
    Optional<Otp> findByOtpAndOtpRefNoAndOtpTypeAndIsOtpUsedFalse(String otp, String otpRef, OtpType otpType);

    Optional<Otp> findByOtpRefNoAndOtpType(String otpRef, OtpType otpType);

    @Query(value = """
            SELECT o
            FROM Otp o
            WHERE o.otp =:otp
            AND o.otpRefNo =:otpRef
            AND o.otpType =:otpType
            AND o.customer.id =:customerId
            AND o.isOtpUsed = false
            """)
    Optional<Otp> checkOtp(@Param("otp") String otp, @Param("otpRef") String otpRef, @Param("otpType") OtpType otpType, @Param("customerId") Long customerId);
}
