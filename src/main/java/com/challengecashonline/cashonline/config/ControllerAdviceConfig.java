package com.challengecashonline.cashonline.config;

import com.challengecashonline.cashonline.model.exception.DuplicatedUserException;
import com.challengecashonline.cashonline.model.exception.LoanNotFoundException;
import com.challengecashonline.cashonline.model.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class ControllerAdviceConfig {

    @ExceptionHandler(value = DuplicatedUserException.class)
    public ResponseEntity<Map<String, String>> handleDuplicatedException(DuplicatedUserException e) {
        log.error(String.format("[DUPLICATE USER]: %s", e.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("[ERROR]", e.getMessage()));
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException e) {
        log.error(String.format("[USER NOT FOUND]: %s", e.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("[ERROR]", e.getMessage()));
    }

    @ExceptionHandler(value = LoanNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleLoanNotFoundException(UserNotFoundException e) {
        log.error(String.format("[USER NOT FOUND]: %s", e.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("[ERROR]", e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        log.error(String.format("[WRONG VALIDATION]: %s", e.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("[ERROR]", Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())));
    }

}
