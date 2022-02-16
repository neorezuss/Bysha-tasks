package com.example.task4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), System.currentTimeMillis());
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
    public ResponseEntity<Object> handleValidationException(ValidationException exception) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleTokenValidationException(TokenValidationException exception) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
    }
}
