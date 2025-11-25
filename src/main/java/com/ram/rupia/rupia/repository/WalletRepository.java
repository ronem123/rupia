package com.ram.rupia.rupia.repository;


import com.ram.rupia.rupia.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ram Mandal on 25/11/2025
 *
 * @System: Apple M1 Pro
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
