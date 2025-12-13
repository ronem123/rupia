package com.ram.rupia.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Created by Ram Mandal on 25/11/2025
 *
 * @System: Apple M1 Pro
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "wallet_tbl"
)
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Here, Wallet owns the relationship
     * A wallet is always associated with one customer
     * A wallet cannot exist without a customer
     * Business: Customer registers → BO admin approves → Wallet is created later → wallet must always belong to customer.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "customer_id",
            nullable = false,
            unique = true,
            foreignKey = @ForeignKey(name = "fk_wallet_customer")
    )
    private Customer customer;

    @Column(name = "wallet_size")
    private int walletSize;

    @Column(name = "wallet_balance")
    private BigDecimal walletBalance;
}
