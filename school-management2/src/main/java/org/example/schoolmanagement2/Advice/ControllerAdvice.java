package org.example.schoolmanagement2.Advice;

import org.example.schoolmanagement2.Api.ApiResponse;
import org.example.schoolmanagement2.Api.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> ApiException(ApiException e) {

        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }
}
