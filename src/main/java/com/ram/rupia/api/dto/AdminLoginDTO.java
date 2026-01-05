/**
 * Author: Ram Mandal
 * Created on @System: Apple M1 Pro
 * User:rammandal
 * Date:04/01/2026
 * Time:23:17
 */


package com.ram.rupia.api.dto;

import com.ram.rupia.domain.enums.UserRole;

public record AdminLoginDTO(String mobileNumber,
                            String userName,
                            UserRole userRole,
                            String accessToken) {
}