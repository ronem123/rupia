package com.ram.rupia.config;


import com.ram.rupia.api.dto.OtpDTO;
import com.ram.rupia.domain.entity.Otp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by Ram Mandal on 03/12/2025
 *
 * @System: Apple M1 Pro
 */

@Mapper(componentModel = "spring")
public interface OtpMapper {
    @Mapping(target = "otpRef", source = "otpRefNo")
    @Mapping(target = "customerId", source = "customer.id")
    OtpDTO toOtpDto(Otp otp);

    Otp toOtp(OtpDTO dto);
}
