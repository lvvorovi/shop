package com.shop.core.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<ErrorDto> handle(Exception ex) {
        if (ex instanceof MethodArgumentNotValidException) {
            String message = ((MethodArgumentNotValidException) ex).getBindingResult()
                    .getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));

            return ResponseEntity.badRequest().body(new ErrorDto(message));
        }
        if (ex instanceof ValidationException) {
            return ResponseEntity.badRequest()
                    .body(new ErrorDto(ex.getMessage()));
        }

        return ResponseEntity.status(500).body(new ErrorDto(ex.getMessage()));
    }
}
