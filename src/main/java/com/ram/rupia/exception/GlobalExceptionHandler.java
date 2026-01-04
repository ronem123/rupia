package com.ram.rupia.exception;


import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

/**
 * Created by Ram Mandal on 04/12/2025
 *
 * @System: Apple M1 Pro
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //Http-Status: 400
    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    public ResponseEntity<ApiErrorResponse> handleBadRequest(BadRequestException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(false, HttpStatus.BAD_REQUEST, ex.getMessage(), Instant.now());
        return new ResponseEntity<>(errorResponse, errorResponse.errorCode());
    }

    // Http-Status: 401
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiErrorResponse> handleAuthenticationException(AuthenticationException ae) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(false, HttpStatus.UNAUTHORIZED, "Authentication failed: " + ae.getMessage(), Instant.now());
        return new ResponseEntity<>(errorResponse, errorResponse.errorCode());
    }

    // Http-Status: 401
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiErrorResponse> handleJwtException(JwtException jex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(false, HttpStatus.UNAUTHORIZED, "Invalid JWT token: " + jex.getMessage(), Instant.now());
        return new ResponseEntity<>(errorResponse, errorResponse.errorCode());
    }

    // Http-Status: 403
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(AccessDeniedException ae) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(false, HttpStatus.FORBIDDEN, "You do not have permission to access this resource", Instant.now());
        return new ResponseEntity<>(errorResponse, errorResponse.errorCode());
    }

    // Http-Status: 404
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNameNotFoundException(UsernameNotFoundException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(false, HttpStatus.NOT_FOUND, "User not found: " + ex.getMessage(), Instant.now());
        return new ResponseEntity<>(errorResponse, errorResponse.errorCode());
    }

    // Http-Status: 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(false, HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred." + ex.getMessage(), Instant.now());
        return new ResponseEntity<>(errorResponse, errorResponse.errorCode());
    }
}
