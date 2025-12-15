package com.ram.rupia.service.transaction;


import com.ram.rupia.api.dto.OtpDTO;
import com.ram.rupia.api.dto.TransactionDTO;
import com.ram.rupia.domain.enums.OtpType;
import com.ram.rupia.api.post_request.ConfirmTransactionRequest;
import com.ram.rupia.api.post_request.WalletReloadRequest;
import com.ram.rupia.api.response.CustomerTransactionResponse;
import com.ram.rupia.api.response.InitiateTransactionResponse;

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
