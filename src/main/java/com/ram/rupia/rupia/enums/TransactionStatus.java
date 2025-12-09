package com.ram.rupia.rupia.enums;


/**
 * Created by Ram Mandal on 08/12/2025
 *
 * @System: Apple M1 Pro
 */
public enum TransactionStatus {
    PENDING,        //Initial state when transaction is created
    SUCCESS,        //When transaction is done and balance is updated
    FAILED,         //When transaction is failed due to insufficient balance, timeout, internal server error
    CANCELED,       //When user tap to cancel the transaction intentionally
    EXPIRED         //When Otp is expired
}
