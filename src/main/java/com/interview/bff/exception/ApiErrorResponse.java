package com.interview.bff.exception;

import lombok.*;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiErrorResponse {
    private String error;
    private String message;
    private Instant timestamp;
    private int status;
    private String path;
    private Map<String, Object> details;
}