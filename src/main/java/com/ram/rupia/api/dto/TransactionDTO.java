package com.ram.rupia.api.dto;


import com.ram.rupia.domain.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

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
@ToString
public class TransactionDTO {
    private Long id;
    private Long walletId;
    private Long senderWalletId;    //null for self wallet reload
    private BigDecimal amount;
    private TransactionType transactionType;
    private String remarks;
    private LocalDate createdAt;
    private String referenceNo;
}
