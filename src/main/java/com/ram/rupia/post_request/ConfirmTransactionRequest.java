package com.ram.rupia.post_request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Ram Mandal on 09/12/2025
 *
 * @System: Apple M1 Pro
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmTransactionRequest {
    private String otp;
    private String otpRef;
    private String transactionRef;
    private Long customerId;
}
