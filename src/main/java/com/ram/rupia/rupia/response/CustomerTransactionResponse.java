package com.ram.rupia.rupia.response;


import com.ram.rupia.rupia.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Ram Mandal on 27/11/2025
 *
 * @System: Apple M1 Pro
 */
@Data
@AllArgsConstructor
public class CustomerTransactionResponse {
    private String name;
    private BigDecimal amount;
    private TransactionType transactionType;
    private BigDecimal walletBalance;
    private int walletSize;
}
