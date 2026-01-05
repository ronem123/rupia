package com.ram.rupia.api.post_request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Ram Mandal on 25/11/2025
 *
 * @System: Apple M1 Pro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletRequestBody {
    private Long customerId;
    private int walletSize;
    private BigDecimal walletBalance;
}
