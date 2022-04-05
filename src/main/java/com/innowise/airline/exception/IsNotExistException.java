package com.innowise.airline.exception;

//TODO: Поскольку Exception работает только в одном направлении(возбуждается, если не найден объект), предлагаю инкапсулировать сообщение в конструктор.
public class IsNotExistException extends RuntimeException{

    private final String value;
    private final String method;

    public IsNotExistException(String value, String method) {
        this.value = value;
        this.method = method;
    }

    public IsNotExistException(String message, String value, String method) {
        super(message);
        this.value = value;
        this.method = method;
    }



}
