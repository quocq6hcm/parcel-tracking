package com.interview.bff.controllerAdvice;

import com.interview.bff.exception.ApiErrorResponse;
import com.interview.bff.exception.GuestNotAvailableException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GuestNotAvailableException.class)
    public ResponseEntity<ApiErrorResponse> handleGuestNotAvailable(
            GuestNotAvailableException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponse response = ApiErrorResponse.builder()
                .error("GUEST_NOT_AVAILABLE")
                .message("The guest is not available")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .details(Map.of("guestId", ex.getGuestId()))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ApiErrorResponse> notFound(
            HttpClientErrorException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponse response = ApiErrorResponse.builder()
                .error("NOT_FOUND")
                .message("Target entity cannot be found.")
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }
}