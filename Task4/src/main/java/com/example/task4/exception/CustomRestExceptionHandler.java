package com.example.task4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.CONFLICT, exception.getLocalizedMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException exception) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.CONFLICT, exception.getLocalizedMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleElixirNotFoundException(ElixirNotFoundException exception) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleIngredientNotFoundException(IngredientNotFoundException exception) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        StringBuilder errors = new StringBuilder();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.append(fieldName).append(": ").append(errorMessage).append("; ");
        });
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.BAD_REQUEST, errors.toString(), System.currentTimeMillis());
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleTokenValidationException(TokenValidationException exception) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
    }
}
