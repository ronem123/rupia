package com.ram.rupia.dto;


import lombok.AllArgsConstructor;
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
public class WalletDTO {
    private Long customerId;
    private int walletSize;
    private BigDecimal walletBalance;
}
