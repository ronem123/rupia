package com.ram.rupia.repository;


import com.ram.rupia.domain.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by Ram Mandal on 25/11/2025
 *
 * @System: Apple M1 Pro
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Modifying
    @Query("UPDATE Wallet w SET w.walletBalance = w.walletBalance+?2 WHERE w.id = ?1")
    int addBalance(Long walletId, BigDecimal amount);


}
