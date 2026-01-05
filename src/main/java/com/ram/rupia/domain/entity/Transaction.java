package com.ram.rupia.domain.entity;


import com.ram.rupia.domain.enums.TransactionStatus;
import com.ram.rupia.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Ram Mandal on 26/11/2025
 *
 * @System: Apple M1 Pro
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transaction_tbl")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    //sender wallet (only used for TransactionType.Transfer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_wallet_id", nullable = true)
    private Wallet senderWallet;

    @Column(name = "transaction_amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String remarks;

    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name = "transaction_reference_no", nullable = false)
    private String referenceNo;

    @Column(name = "transaction_status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
}
