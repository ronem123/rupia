package com.ram.rupia.service.wallet;


import com.ram.rupia.config.WalletMapper;
import com.ram.rupia.dto.WalletDTO;
import com.ram.rupia.entity.Customer;
import com.ram.rupia.entity.Wallet;
import com.ram.rupia.enums.CustomerStatus;
import com.ram.rupia.repository.CustomerRepository;
import com.ram.rupia.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Ram Mandal on 25/11/2025
 *
 * @System: Apple M1 Pro
 */

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Transactional
    @Override
    public Boolean createNewWallet(Customer customer) {

        boolean wasWalletCreated = false;

        if (customer.getWallet() == null) {
            Wallet newWallet = Wallet.builder()
                    .customer(customer)
                    .walletSize(2000)
                    .walletBalance(BigDecimal.ZERO)
                    .build();

            walletRepository.save(newWallet);
            wasWalletCreated = true;
        }
        return wasWalletCreated;
    }
}
