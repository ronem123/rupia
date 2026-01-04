/**
 * Author: Ram Mandal
 * Created on @System: Apple M1 Pro
 * User:rammandal
 * Date:19/12/2025
 * Time:09:45
 */


package com.ram.rupia.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ApiErrorResponse(Boolean status,
                               HttpStatus errorCode,
                               String message,
                               Instant time) {
}