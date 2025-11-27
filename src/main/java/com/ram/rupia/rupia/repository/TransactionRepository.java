package com.ram.rupia.rupia.repository;


import com.ram.rupia.rupia.entity.Transaction;
import com.ram.rupia.rupia.response.CustomerTransactionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Ram Mandal on 27/11/2025
 *
 * @System: Apple M1 Pro
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    /**
     * select ct.customer_full_name ,tt.transaction_amount ,tt.transaction_type ,wt.wallet_balance ,wt.wallet_size
     * from customer_tbl ct
     * inner join wallet_tbl wt on wt.customer_id = ct.id
     * inner join transaction_tbl tt on tt.wallet_id = wt.id
     */
    @Query(value = """
            SELECT new com.ram.rupia.rupia.enums.TransactionType.CustomerTransactionResponse(
                ct.name,
                tt.amount,
                tt.transactionType,
                wt.walletBalance,
                wt.walletSize
            )
            FROM Customer ct
            INNER JOIN ct.wallet wt
            INNER JOIN wt.transaction tt
            """)
    List<CustomerTransactionResponse> getCustomerTransaction();
}
