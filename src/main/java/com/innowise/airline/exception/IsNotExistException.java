package com.innowise.airline.exception;

//TODO: чем плох ObjectNotFoundException? В целом, название эксепшна с глагола - такое себе
public class IsNotExistException extends RuntimeException {
//TODO: где геттеры для полей?
    private final String message;
    private final String method;

    public IsNotExistException() {
//        TODO: литерал в константу
        this.message = "is not exist";
        method = null;
    }

    public IsNotExistException(String method) {
        this.message = "is not exist";
        this.method = method;
    }

}
