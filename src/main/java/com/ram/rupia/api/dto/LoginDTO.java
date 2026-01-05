/**
 * Author: Ram Mandal
 * Created on @System: Apple M1 Pro
 * User:rammandal
 * Date:23/12/2025
 * Time:11:33
 */


package com.ram.rupia.api.dto;

/**
 * Returns when Otp verification is success during Customer login
 *
 * @param customerId
 * @param accessToken
 * @param refreshToken
 */
public record LoginDTO(Long customerId, String accessToken, String refreshToken) {
}