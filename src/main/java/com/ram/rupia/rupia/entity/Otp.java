package com.ram.rupia.rupia.entity;


import com.ram.rupia.rupia.enums.OtpType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Ram Mandal on 03/12/2025
 *
 * @System: Apple M1 Pro
 */
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "otp_tbl")
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OtpType otpType;

    @Column(nullable = false, length = 6)
    private String otp;

    @Column(name = "otp_ref", nullable = false, unique = true)
    private String otpRefNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name = "otp_expiry_time", nullable = false)
    private LocalDateTime expiryTime;

    @Column(name = "is_otp_used", nullable = false, columnDefinition = "boolean default false")
    private boolean isOtpUsed = false;

}
