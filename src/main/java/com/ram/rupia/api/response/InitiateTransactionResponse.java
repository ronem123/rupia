package com.ram.rupia.api.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ram Mandal on 09/12/2025
 *
 * @System: Apple M1 Pro
 */
@Getter
@Setter
@Builder
public class InitiateTransactionResponse {
    private String transactionRef;
    private String otpRef;
}
