package com.ram.rupia.repository;

import com.ram.rupia.domain.entity.Admin;
import com.ram.rupia.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByEmail(String email);
}
