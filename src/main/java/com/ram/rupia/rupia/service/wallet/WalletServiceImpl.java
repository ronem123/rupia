package com.ram.rupia.rupia.service.wallet;


import com.ram.rupia.rupia.config.TransactionMapper;
import com.ram.rupia.rupia.config.WalletMapper;
import com.ram.rupia.rupia.dto.TransactionDTO;
import com.ram.rupia.rupia.dto.WalletDTO;
import com.ram.rupia.rupia.entity.Customer;
import com.ram.rupia.rupia.entity.Transaction;
import com.ram.rupia.rupia.entity.Wallet;
import com.ram.rupia.rupia.enums.CustomerStatus;
import com.ram.rupia.rupia.enums.TransactionType;
import com.ram.rupia.rupia.post_request.WalletReloadRequest;
import com.ram.rupia.rupia.post_request.WalletRequestBody;
import com.ram.rupia.rupia.repository.CustomerRepository;
import com.ram.rupia.rupia.repository.TransactionRepository;
import com.ram.rupia.rupia.repository.WalletRepository;
import com.ram.rupia.rupia.response.CustomerTransactionResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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
    private final TransactionRepository transactionRepository;
    private final WalletMapper walletMapper;
    private final TransactionMapper transactionMapper;


    @Transactional
    @Override
    public TransactionDTO reloadWallet(WalletReloadRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId()).orElseThrow(() ->
                new RuntimeException("Sorry ! wallet not found"));

        //proceed only if the wallet exists
        //check if the amount is less than or equal to 0, else throw an exception
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }

        //add balance to wallet
        walletRepository.addBalance(request.getWalletId(), request.getAmount());

        //update transaction table
        Transaction transaction = Transaction.builder()
                .wallet(wallet)
                .amount(request.getAmount())
                .transactionType(TransactionType.CREDIT)
                .remarks(request.getRemarks())
                .referenceNo(UUID.randomUUID().toString())
                .build();

        Transaction newTransaction = transactionRepository.save(transaction);

        //return Transaction dto
        return transactionMapper.toTransactionDTO(newTransaction);
    }

    @Override
    public List<CustomerTransactionResponse> getCustomerTransaction() {
        return transactionRepository.getCustomerTransaction();
    }
}
