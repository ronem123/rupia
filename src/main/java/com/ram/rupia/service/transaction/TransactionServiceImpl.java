package com.ram.rupia.service.transaction;


import com.ram.rupia.config.TransactionMapper;
import com.ram.rupia.dto.OtpDTO;
import com.ram.rupia.dto.TransactionDTO;
import com.ram.rupia.entity.Otp;
import com.ram.rupia.entity.Transaction;
import com.ram.rupia.entity.Wallet;
import com.ram.rupia.enums.OtpType;
import com.ram.rupia.enums.TransactionStatus;
import com.ram.rupia.enums.TransactionType;
import com.ram.rupia.exception.BadRequestException;
import com.ram.rupia.post_request.ConfirmTransactionRequest;
import com.ram.rupia.post_request.VerifyOtpRequest;
import com.ram.rupia.post_request.WalletReloadRequest;
import com.ram.rupia.repository.TransactionRepository;
import com.ram.rupia.repository.WalletRepository;
import com.ram.rupia.response.CustomerTransactionResponse;
import com.ram.rupia.response.InitiateTransactionResponse;
import com.ram.rupia.service.otp.OtpService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ram Mandal on 09/12/2025
 *
 * @System: Apple M1 Pro
 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final WalletRepository walletRepository;
    private final OtpService otpService;

    @Transactional
    @Override
    public InitiateTransactionResponse loadMoneyToWallet(WalletReloadRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId()).orElseThrow(() -> new RuntimeException("Sorry Wallet not found"));

        //proceed only if the wallet exists
        //check if the amount is less than or equal to 0, else throw an exception
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be greater than 0");
        }

        //add balance to wallet
//        int updated = walletRepository.addBalance(request.getWalletId(), request.getAmount());
//        if (updated != 1) {
//            throw new RuntimeException("Failed to update wallet balance");
//        }

        //1. create transaction
        Transaction transaction = Transaction.builder()
                .wallet(wallet)
                .amount(request.getAmount())
                .transactionType(TransactionType.RELOAD)
                .transactionStatus(TransactionStatus.PENDING)
                .remarks(request.getRemarks())
                .referenceNo(UUID.randomUUID().toString())
                .build();

        //2. Save the initial transaction
        transactionRepository.save(transaction);

        //3. generate otp for this transaction
        OtpDTO otpDTO = otpService.generateOtp(wallet.getCustomer().getId(), OtpType.TRANSACTION);

        //4. return transactionRef + otpRef
        return InitiateTransactionResponse.builder()
                .transactionRef(transaction.getReferenceNo())
                .otpRef(otpDTO.getOtpRef())
                .build();

    }

    @Transactional
    @Override
    public TransactionDTO confirmTransaction(ConfirmTransactionRequest request) {
        VerifyOtpRequest verifyOtpRequest = new VerifyOtpRequest(request.getOtp(), request.getOtpRef(), request.getCustomerId(), OtpType.TRANSACTION);
        //1. Verify OTP first
        Boolean isOtpVerified = otpService.verifyOtp(verifyOtpRequest);
        if (!isOtpVerified) {
            throw new BadRequestException("Invalid OTP");
        }

        //2. fetch PENDING transaction with provided transactionRef;
        Transaction transaction = transactionRepository.findByReferenceNo(request.getTransactionRef()).orElseThrow(
                () -> new BadRequestException("Invalid Transaction Reference no"));

        if (transaction.getTransactionStatus() != TransactionStatus.PENDING) {
            throw new BadRequestException("Transaction already processed");
        }

        //3. Update wallet balance
        walletRepository.addBalance(transaction.getWallet().getId(), transaction.getAmount());

        //4. Update transaction to SUCCESS
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //5. Return Transaction DTO
        return transactionMapper.toTransactionDTO(transaction);
    }

    @Transactional
    @Override
    public OtpDTO resendOtp(String otpRef, OtpType otpType) {

        Otp oldOtp = otpService.getOtp(otpRef, otpType);

        //mark previous otp as expired/used
        oldOtp.setOtpUsed(true);

        //generate new otp
        return otpService.generateOtp(oldOtp.getCustomer().getId(), otpType);
    }

    @Override
    public List<CustomerTransactionResponse> getCustomerTransaction() {
        return transactionRepository.getCustomerTransaction();
    }
}
