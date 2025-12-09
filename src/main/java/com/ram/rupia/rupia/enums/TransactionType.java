package com.ram.rupia.rupia.enums;


/**
 * Created by Ram Mandal on 26/11/2025
 *
 * @System: Apple M1 Pro
 */

public enum TransactionType {
    CREDIT,     // When balance is added to wallet
    DEBIT,      // Deducting the amount from wallet while making payment
    TRANSFER,   // Make wallet to wallet transfer
    RELOAD      // When initiating the mobile reload but balance is not loaded yet
}
