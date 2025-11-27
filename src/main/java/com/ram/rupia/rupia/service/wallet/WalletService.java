package com.ram.rupia.rupia.service.wallet;


import com.ram.rupia.rupia.dto.CustomerWithWalletDTO;
import com.ram.rupia.rupia.dto.TransactionDTO;
import com.ram.rupia.rupia.dto.WalletDTO;
import com.ram.rupia.rupia.post_request.WalletReloadRequest;
import com.ram.rupia.rupia.post_request.WalletRequestBody;
import com.ram.rupia.rupia.response.CustomerTransactionResponse;

import java.util.List;

/**
 * Created by Ram Mandal on 25/11/2025
 *
 * @System: Apple M1 Pro
 */
public interface WalletService {

    TransactionDTO reloadWallet(WalletReloadRequest request);

    List<CustomerTransactionResponse> getCustomerTransaction();
}
