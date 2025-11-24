package com.ram.rupia.rupia.entity;

import com.ram.rupia.rupia.enums.CustomerStatus;
import com.ram.rupia.rupia.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created by Ram Mandal on 16/11/2025
 *
 * @System: Apple M1 Pro
 */

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "customer_tbl",
        uniqueConstraints = {@UniqueConstraint(
                name = "unique_mobile_id_no",
                columnNames = {"customer_mob_no", "customer_id_number"})},

        //indexes help during filter and search
        indexes = {
                @Index(name = "idx_customer_id_number", columnList = "customer_id_number"),
                @Index(name = "idx_customer_dob", columnList = "customer_date_of_birth")})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_full_name", nullable = false)
    private String name;

    @Column(name = "customer_date_of_birth", nullable = false)
    private LocalDate birthDate;

    @Column(name = "customer_mob_no", unique = true, nullable = false)
    private String contact;

    @Column(name = "customer_id_number", unique = true, nullable = false)
    private String idNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_gender", nullable = false)
    private Gender gender;

    @Column(name = "customer_email", unique = true, nullable = false)
    private String email;

    @Column(name = "customer_address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_status", nullable = false)
    private CustomerStatus status;
}
