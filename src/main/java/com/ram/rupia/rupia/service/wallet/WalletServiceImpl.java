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


    @Transactional
    @Override
    public WalletDTO createWallet(WalletRequestBody body) {
        Customer customer = customerRepository.findById(body.getCustomerId()).orElseThrow(() ->
                new RuntimeException("Sorry! but customer not found"));

        Wallet wallet = mapper.toWallet(body);
        wallet.setCustomer(customer);

        Wallet newWallet = walletRepository.save(wallet);
        if (customer.getStatus() == CustomerStatus.PENDING) {
            customer.setStatus(CustomerStatus.ACTIVE);
        }
        /**
         * We do not need to explicitly save the customer, as we are under the @Transactional state
         * whenever any update happens to the customer, dirty checking will be there, and if any changes
         * It will automatically update it.
         * Note: since we are working on the same customer object. So, no new row will be inserted. JPA will manage it
         * automatically. like this
         * UPDATE customer_tbl
         * SET customer_status = 'ACTIVE'
         * WHERE id = <customer_id>;
         */
        return mapper.toWalletDTO(newWallet);
    }
}
