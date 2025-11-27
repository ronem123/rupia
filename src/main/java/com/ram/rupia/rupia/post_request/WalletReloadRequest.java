package com.ram.rupia.rupia.post_request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Ram Mandal on 27/11/2025
 *
 * @System: Apple M1 Pro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletReloadRequest {
    private Long walletId;
    private BigDecimal amount;
    private String remarks;
}
