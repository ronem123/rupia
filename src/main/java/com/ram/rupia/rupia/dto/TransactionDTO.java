package com.ram.rupia.rupia.dto;


import com.ram.rupia.rupia.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Ram Mandal on 26/11/2025
 *
 * @System: Apple M1 Pro
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TransactionDTO {
    private Long id;
    private Long walletId;
    private BigDecimal amount;
    private TransactionType transactionType;
    private String remarks;
    private LocalDate createdAt;
    private String referenceNo;
}
