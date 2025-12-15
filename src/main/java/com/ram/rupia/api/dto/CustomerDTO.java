package com.ram.rupia.api.dto;

import com.ram.rupia.domain.enums.CustomerStatus;
import com.ram.rupia.domain.enums.Gender;
import lombok.*;

import java.time.LocalDate;

/**
 * Created by Ram Mandal on 16/11/2025
 *
 * @System: Apple M1 Pro
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String contact;
    private String idNumber;
    private Gender gender;
    private String email;
    private String address;
    private CustomerStatus customerStatus;
    private LocalDate createdAt;
}
