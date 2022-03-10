package com.example.task4.exception;

public class ElixirNotFoundException extends RuntimeException{
    public ElixirNotFoundException() {
    }

    public ElixirNotFoundException(String message) {
        super(message);
    }

    public ElixirNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElixirNotFoundException(Throwable cause) {
        super(cause);
    }
}
