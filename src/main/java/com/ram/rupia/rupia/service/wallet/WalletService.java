package com.ram.rupia.rupia.service.wallet;


import com.ram.rupia.rupia.dto.WalletDTO;
import com.ram.rupia.rupia.entity.Customer;

/**
 * Created by Ram Mandal on 25/11/2025
 *
 * @System: Apple M1 Pro
 */
public interface WalletService {

    Boolean createNewWallet(Customer customer);
}
