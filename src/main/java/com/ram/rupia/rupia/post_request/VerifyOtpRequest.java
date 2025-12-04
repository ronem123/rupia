package com.ram.rupia.rupia.post_request;


import lombok.*;

/**
 * Created by Ram Mandal on 04/12/2025
 *
 * @System: Apple M1 Pro
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyOtpRequest {
    private String otp;
    private String otpRef;
}
