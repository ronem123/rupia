package com.ram.rupia.service.transaction;


import com.ram.rupia.dto.OtpDTO;
import com.ram.rupia.dto.TransactionDTO;
import com.ram.rupia.entity.Otp;
import com.ram.rupia.enums.OtpType;
import com.ram.rupia.post_request.ConfirmTransactionRequest;
import com.ram.rupia.post_request.WalletReloadRequest;
import com.ram.rupia.response.CustomerTransactionResponse;
import com.ram.rupia.response.InitiateTransactionResponse;

import java.util.List;

/**
 * Created by Ram Mandal on 08/12/2025
 *
 * @System: Apple M1 Pro
 */


public interface TransactionService {
    InitiateTransactionResponse loadMoneyToWallet(WalletReloadRequest body);

    TransactionDTO confirmTransaction(ConfirmTransactionRequest request);

    OtpDTO resendOtp(String otpRef, OtpType otpType);

    List<CustomerTransactionResponse> getCustomerTransaction();
}
