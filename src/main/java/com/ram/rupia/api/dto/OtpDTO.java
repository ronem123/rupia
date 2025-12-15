package com.ram.rupia.api.dto;


import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by Ram Mandal on 03/12/2025
 *
 * @System: Apple M1 Pro
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpDTO {
    private String otp;
    private String otpRef;
    private LocalDateTime expiryTime;
    private Long customerId;
}
