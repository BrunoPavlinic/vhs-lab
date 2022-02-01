package com.example.vhs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.el.MethodNotFoundException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleException(HttpRequestMethodNotSupportedException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("HTTP method not supported - " + exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(NullPointerException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Null pointer exception - " + exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Illegal argument exception - " + exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Access denied exception - " + exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Method argument not valid exception - " + exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(MethodNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Method not found exception - " + exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Exception - " + exception.getMessage());
    }
}
