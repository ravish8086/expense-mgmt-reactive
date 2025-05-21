package com.example.expensemgmt.core;

import com.example.expensemgmt.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


        @ExceptionHandler(ResourceNotFoundException.class)
        public Mono<ResponseEntity<Map<String, Object>>> handleNotFound(ResourceNotFoundException ex) {
            Map<String, Object> response = Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", HttpStatus.NOT_FOUND.value(),
                    "error", ex.getMessage()
            );
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(response));
        }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleGeneral(Exception ex) {
        return Mono.just(new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "error", ex.getMessage()
                ), HttpStatus.INTERNAL_SERVER_ERROR
        ));
    }

}
