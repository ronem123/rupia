/**
 * Author: Ram Mandal
 * Created on @System: Apple M1 Pro
 * User:rammandal
 * Date:05/01/2026
 * Time:12:17
 */


package com.ram.rupia.api.dto;

public record AdminDTO(
        String id,
        String fullName,
        String email,
        String role,
        String status
) {
}