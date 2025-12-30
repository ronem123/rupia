/**
 * Author: Ram Mandal
 * Created on @System: Apple M1 Pro
 * User:rammandal
 * Date:19/12/2025
 * Time:09:45
 */


package com.ram.rupia.exception;

import java.time.Instant;

public record ApiErrorResponse(Boolean status,
                               String errorCode,
                               String message,
                               Instant time) {
}