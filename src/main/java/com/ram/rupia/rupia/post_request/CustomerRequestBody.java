package com.ram.rupia.rupia.post_request;


import com.ram.rupia.rupia.enums.CustomerStatus;
import com.ram.rupia.rupia.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by Ram Mandal on 16/11/2025
 *
 * @System: Apple M1 Pro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestBody {
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String contact;
    private String idNumber;
    private String email;
    private String address;
}