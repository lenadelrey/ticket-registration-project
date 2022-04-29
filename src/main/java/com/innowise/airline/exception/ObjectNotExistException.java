package com.innowise.airline.exception;

import lombok.Getter;

//TODO: чем плох ObjectNotFoundException? В целом, название эксепшна с глагола - такое себе
@Getter
public class ObjectNotExistException extends RuntimeException {
    private static final String MESSAGE = "Is not exist";
    private final String message;
    private final String method;

    public ObjectNotExistException() {
        this.message = MESSAGE;
        method = null;
    }

    public ObjectNotExistException(String method) {
        this.message = MESSAGE;
        this.method = method;
    }
}
