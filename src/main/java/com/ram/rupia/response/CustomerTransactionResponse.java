package com.ram.rupia.response;


import java.math.BigDecimal;

/**
 * Created by Ram Mandal on 27/11/2025
 *
 * @System: Apple M1 Pro
 */

public interface CustomerTransactionResponse {
    String getCustomerName();

    BigDecimal getAmount();

    String getTransactionType();

    BigDecimal getWalletBalance();

    int getWalletSize();
}
