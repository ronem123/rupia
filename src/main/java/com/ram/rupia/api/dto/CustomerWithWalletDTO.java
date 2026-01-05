package com.ram.rupia.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Ram Mandal on 26/11/2025
 *
 * @System: Apple M1 Pro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWithWalletDTO {
    private Long id;
    private String name;
    private BigDecimal walletBalance;
}
