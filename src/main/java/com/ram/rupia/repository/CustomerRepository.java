package com.ram.rupia.repository;


import com.ram.rupia.dto.CustomerDTO;
import com.ram.rupia.dto.CustomerWithWalletDTO;
import com.ram.rupia.entity.Customer;
import com.ram.rupia.post_request.CustomerLoginRequestBody;
import com.ram.rupia.post_request.CustomerRequestBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ram Mandal on 16/11/2025
 *
 * @System: Apple M1 Pro
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //if use native query set nativeQuery = true
//    @Query(value = """
//            SELECT ct.id ,ct.customer_full_name ,wt.wallet_balance  FROM customer_tbl ct\s
//            INNER JOIN wallet_tbl wt\s
//            ON ct.id =wt.customer_id\s
//            WHERE ct.id = :customerId
//            ORDER BY ct.customer_full_name  ASC""", nativeQuery = true)
//    List<CustomerWithWalletDTO> getCustomerWithWallet(Long customerId);


    //if use jql no need to set nativeQuery = true
    //also no need to provide the actual table column name, instead provide Entity field name
    //also no need to ad ON clause like below; if using JPQL. It knows which column is mapped with which one
    //ON ct.id =wt.customer_id\s
    @Query(value = """
            SELECT new com.ram.rupia.dto.CustomerWithWalletDTO(ct.id,ct.name,wt.walletBalance)\s
            FROM Customer ct\s
            INNER JOIN ct.wallet wt\s
            WHERE ct.id = ?1
            ORDER BY ct.name  ASC""")
    List<CustomerWithWalletDTO> getCustomerWithWallet(Long customerId);


    Optional<Customer> findByContact(String contact);
}
