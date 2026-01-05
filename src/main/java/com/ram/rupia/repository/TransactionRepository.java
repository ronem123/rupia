package com.ram.rupia.repository;


import com.ram.rupia.domain.entity.Transaction;
import com.ram.rupia.api.response.CustomerTransactionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ram Mandal on 27/11/2025
 *
 * @System: Apple M1 Pro
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = """
             SELECT\s
             ct.customer_full_name as customerName,
             tt.transaction_amount as amount,
             tt.transaction_type as transactionType,
             wt.wallet_balance as walletBalance,
             wt.wallet_size as walletSize
             FROM customer_tbl ct
             INNER JOIN wallet_tbl wt on wt.customer_id = ct.id
             INNER JOIN transaction_tbl tt on tt.wallet_id = wt.id
            \s""", nativeQuery = true)
    List<CustomerTransactionResponse> getCustomerTransaction();

    Optional<Transaction> findByReferenceNo(String transactionRef);
}
