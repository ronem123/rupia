package com.ram.rupia.rupia.config;


import com.ram.rupia.rupia.dto.TransactionDTO;
import com.ram.rupia.rupia.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by Ram Mandal on 26/11/2025
 *
 * @System: Apple M1 Pro
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "walletId", source = "wallet.id")
    @Mapping(target = "senderWalletId", source = "senderWallet.id")
    TransactionDTO toTransactionDTO(Transaction entity);

    /**
     * Ignore wallet object setup inside the Transaction Entity,
     * As we will only have the walletId and senderWalletId, the Transaction will hold the entire wallet entity detail
     * for wallet and senderWallet
     * And we can't map that Long type ID with Wallet Type. Instead, we will set this value from the Service
     *
     * @param dto
     * @return
     */
    @Mapping(target = "wallet", ignore = true)
    @Mapping(target = "senderWallet", ignore = true)
    Transaction toTransaction(TransactionDTO dto);
}
