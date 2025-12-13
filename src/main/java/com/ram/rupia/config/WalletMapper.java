package com.ram.rupia.config;


import com.ram.rupia.dto.WalletDTO;
import com.ram.rupia.entity.Wallet;
import com.ram.rupia.post_request.WalletRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by Ram Mandal on 25/11/2025
 *
 * @System: Apple M1 Pro
 */
@Mapper(componentModel = "spring")
public interface WalletMapper {

    @Mapping(target = "customerId", source = "customer.id")
    WalletDTO toWalletDTO(Wallet entity);

    /**
     * Here we are ignoring the mapping for the customer because
     * RequestBody does not have a customer; it has only a customerId
     * and MapStruct cannot map the customerId to the customer object, so we ignore it during mapping
     * and will be provided in the service during wallet creation
     */
    @Mapping(target = "customer", ignore = true)
    Wallet toWallet(WalletRequestBody body);

    @Mapping(target = "customer", ignore = true)
    Wallet toWallet(WalletDTO dto);

}
