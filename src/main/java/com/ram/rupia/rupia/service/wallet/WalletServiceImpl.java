package com.ram.rupia.rupia.service.wallet;


import com.ram.rupia.rupia.config.WalletMapper;
import com.ram.rupia.rupia.dto.WalletDTO;
import com.ram.rupia.rupia.entity.Customer;
import com.ram.rupia.rupia.entity.Wallet;
import com.ram.rupia.rupia.enums.CustomerStatus;
import com.ram.rupia.rupia.post_request.WalletRequestBody;
import com.ram.rupia.rupia.repository.CustomerRepository;
import com.ram.rupia.rupia.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by Ram Mandal on 25/11/2025
 *
 * @System: Apple M1 Pro
 */

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final CustomerRepository customerRepository;
    private final WalletMapper mapper;


}
