package com.innowise.airline.exception;

public class IsNotExistException extends RuntimeException {

    private final String message;
    private final String method;

    public IsNotExistException() {
        this.message = "is not exist";
        method = null;
    }

    public IsNotExistException(String method) {
        this.message = "is not exist";
        this.method = method;
    }

}
