package com.ram.rupia.controller;


import com.ram.rupia.dto.TransactionDTO;
import com.ram.rupia.post_request.ConfirmTransactionRequest;
import com.ram.rupia.post_request.WalletReloadRequest;
import com.ram.rupia.response.ApiResponse;
import com.ram.rupia.response.InitiateTransactionResponse;
import com.ram.rupia.service.transaction.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ram Mandal on 04/12/2025
 *
 * @System: Apple M1 Pro
 */
@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")//remove once the front end is hosted on a real web server
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    @PostMapping("/reload")
    public ResponseEntity<ApiResponse<InitiateTransactionResponse>> reloadWallet(@RequestBody WalletReloadRequest request) {
        InitiateTransactionResponse initiateTransactionResponse = transactionService.loadMoneyToWallet(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Wallet reload successfully", initiateTransactionResponse));
    }

    @PostMapping("/confirm")
    public ResponseEntity<ApiResponse<TransactionDTO>> confirmTransaction(@RequestBody ConfirmTransactionRequest request) {
        TransactionDTO transactionDTO = transactionService.confirmTransaction(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Cannot confirm the transaction", transactionDTO));
    }
}
